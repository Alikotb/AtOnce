package com.example.atonce.presentation.forgotpassword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.core.enums.ForgotPasswordEnumMessages
import com.example.atonce.core.utils.ConfirmPasswordHandler
import com.example.atonce.core.utils.EmptyPasswordHandler
import com.example.atonce.core.utils.PasswordCharDigitHandler
import com.example.atonce.core.utils.PasswordLengthHandler
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
                } else {
                    _uiState.value = ForgotPasswordState.EnterEmail(isLoading = false, error = response.message)
                    _message.emit(ForgotPasswordEnumMessages.INVALIDEMAIL.getLocalizedMessage())
                }
            }catch(e: Exception) {
                _uiState.value = ForgotPasswordState.EnterEmail(isLoading = false, error = e.message)
                _message.emit(ForgotPasswordEnumMessages.NETWORKERROR.getLocalizedMessage())
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
                _message.emit(ForgotPasswordEnumMessages.INVALIDOTP.getLocalizedMessage())
            }
        }
    }

    fun submitNewPassword(email: String, newPassword: String, confirmPassword: String) {
        viewModelScope.launch(handler) {
            try {
                val head = EmptyPasswordHandler(newPassword)
                head.setNext(PasswordLengthHandler(newPassword))
                    .setNext(PasswordCharDigitHandler(newPassword))
                    .setNext(ConfirmPasswordHandler(newPassword, confirmPassword))

                val validationError = head.handle()
                if (validationError != null) {
                    _uiState.value = ForgotPasswordState.SetNewPassword(email, generatedOtp, error = validationError)
                    _message.emit(validationError)
                    return@launch
                }
                val response = resetPasswordUseCase(ResetPasswordRequest(email, generatedOtp, newPassword, confirmPassword))
                if (response.success) {
                    _uiState.value = ForgotPasswordState.SetNewPassword(email, generatedOtp, isLoading = true)
                    delay(1500)
                    _uiState.value = ForgotPasswordState.ResetSuccess(isLoading = false)
                } else {
                    _uiState.value = ForgotPasswordState.SetNewPassword(email, generatedOtp, error = response.message)
                   // _message.emit(response.message)
                }

            }catch(e: Exception) {
                _uiState.value = ForgotPasswordState.SetNewPassword(email, generatedOtp, isLoading = false, error = e.message)
                _message.emit(ForgotPasswordEnumMessages.NETWORKERROR.getLocalizedMessage())
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