package com.example.atonce.presentation.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import com.example.atonce.domain.usecase.LoginUseCase
import com.example.atonce.domain.usecase.SavePharmacyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun login(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            if (email.isBlank() || password.isBlank()) {
                _isLoading.value = false
                _message.emit("Please fill in all fields")
                return@launch
            }
            loginUseCase(email, password)
                .catch { e ->
                    _isLoading.value = false
                    _message.emit("An error occurred")
                }
                .collect { result ->
                    _isLoading.value = false
                    if (result.success == true) {
                        savePharmacyUseCase(result.pharmacy!!)
                        _loginSuccess.emit(true)
                    } else {
                        _message.emit(result.message ?: "Login failed")
                    }
                }
        }
    }
}
