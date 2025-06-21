package com.example.atonce.presentation.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import com.example.atonce.domain.usecase.LoginUseCase
import com.example.atonce.domain.usecase.SavePharmacyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val savePharmacyUseCase: SavePharmacyUseCase,
) : ViewModel() {

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    private val _loginSuccess = MutableSharedFlow<Boolean>()
    val loginSuccess = _loginSuccess.asSharedFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (email.isBlank() || password.isBlank()) {
                _message.emit("Please fill in all fields")
                return@launch
            }
            loginUseCase(email, password)
                .catch { e ->
                    _message.emit("An error occurred")
                }
                .collect { result ->
                    if (result.success == true) {
                        _loginSuccess.emit(true)
                        savePharmacyUseCase(result.pharmacy!!)
                    } else {
                        val passwordErrors = result.errors?.Password?.joinToString("\n") ?: ""
                        val emailErrors = result.errors?.Email?.joinToString("\n") ?: ""
                        val combinedErrors = listOf(passwordErrors, emailErrors)
                            .filter { it.isNotBlank() }
                            .joinToString("\n")

                        _message.emit(
                            combinedErrors.ifBlank { result.message ?: "Login failed" }
                        )
                    }
                }
        }
    }
}
