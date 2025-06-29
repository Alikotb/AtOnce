package com.example.atonce.presentation.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.core.enums.ErrorMessagesEnum
import com.example.atonce.domain.usecase.LoginUseCase
import com.example.atonce.domain.usecase.SavePharmacyUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
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

    val handler = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            _isLoading.value = false
            _message.emit(ErrorMessagesEnum.NETWORKERROR.getLocalizedMessage())
        }
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + handler) {
            if (email.isBlank() || password.isBlank()) {
                _isLoading.value = false
                _message.emit(ErrorMessagesEnum.EMPTYFIELDS.getLocalizedMessage())
                return@launch
            }
            loginUseCase(email, password)
                .catch { e ->
                    _isLoading.value = false
                    _message.emit(ErrorMessagesEnum.LOGINEXCEPTION.getLocalizedMessage())
                }
                .collect { result ->
                    _isLoading.value = false
                    if (result.success == true) {
                        savePharmacyUseCase(result.pharmacy!!)
                        Log.d("Login", "login: ${result}")
                        _loginSuccess.emit(true)
                    } else {
                        _message.emit(ErrorMessagesEnum.LOGINFAILED.getLocalizedMessage())
                    }
                }
        }
    }
}
