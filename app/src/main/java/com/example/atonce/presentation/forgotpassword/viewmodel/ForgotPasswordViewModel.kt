package com.example.atonce.presentation.forgotpassword.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.domain.entity.ForgotPasswordRequest
import com.example.atonce.domain.entity.ForgotPasswordState
import com.example.atonce.domain.entity.ResetPasswordRequest
import com.example.atonce.domain.usecase.ForgotPasswordUseCase
import com.example.atonce.domain.usecase.ResetPasswordUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private var generatedOtp: String = generateOtp()

    private val _uiState = MutableStateFlow<ForgotPasswordState>(ForgotPasswordState.EnterEmail())
    val uiState: StateFlow<ForgotPasswordState> = _uiState.asStateFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    val handler = CoroutineExceptionHandler { _, exception ->

    }

    fun submitEmail(email: String) {
        _uiState.value = ForgotPasswordState.EnterEmail(email = email, isLoading = true)
        viewModelScope.launch(handler) {
            try {
                val response = forgotPasswordUseCase(ForgotPasswordRequest(email, generatedOtp))
                if (response.success) {
                    _uiState.value = ForgotPasswordState.EnterOtp(email)
                    _message.emit(response.message)
                } else {
                    _uiState.value = ForgotPasswordState.EnterEmail(isLoading = false, error = response.message)
                    _message.emit(response.message)
                }
            }catch(e: Exception) {

            }
        }
    }

    fun submitOtp(email: String, otp: String) {
        viewModelScope.launch(handler) {
            _uiState.value = ForgotPasswordState.EnterOtp(email, isLoading = true)
            if (otp == generatedOtp) {
                delay(1500)
                _uiState.value = ForgotPasswordState.SetNewPassword(email, otp)
            } else {
                _uiState.value = ForgotPasswordState.EnterOtp(email, isLoading = false, error = "Invalid OTP")
                _message.emit("Invalid OTP")
            }
        }
    }

    fun submitNewPassword(email: String, newPassword: String, confirmPassword: String) {
        viewModelScope.launch(handler) {
            if (newPassword != confirmPassword) {
                _uiState.value = ForgotPasswordState.SetNewPassword(email, generatedOtp, error = "Passwords do not match")
                _message.emit("Passwords do not match")
                return@launch
            }else {
                val response = resetPasswordUseCase(ResetPasswordRequest(email, generatedOtp, newPassword, confirmPassword))
                if (response.success) {
                    _uiState.value = ForgotPasswordState.SetNewPassword(email, generatedOtp, isLoading = true)
                    _message.emit(response.message)
                    delay(1500)
                    _uiState.value = ForgotPasswordState.ResetSuccess(isLoading = false)
                } else {
                    _uiState.value = ForgotPasswordState.SetNewPassword(email, generatedOtp, error = response.message)
                    _message.emit(response.message)
                }
            }
        }
    }

    fun success() {
        _uiState.value = ForgotPasswordState.ResetSuccess(isLoading = false)
    }

    private fun generateOtp(): String {
        return (10000..99999).random().toString()
    }
}