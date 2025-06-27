package com.example.atonce.presentation.forgotpassword.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.domain.entity.ForgotPasswordRequest
import com.example.atonce.domain.entity.ForgotPasswordState
import com.example.atonce.domain.entity.ResetPasswordRequest
import com.example.atonce.domain.usecase.ForgotPasswordUseCase
import com.example.atonce.domain.usecase.ResetPasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private var generatedOtp: String = generateOtp()

    private val _uiState = MutableStateFlow<ForgotPasswordState>(ForgotPasswordState.EnterEmail())
    val uiState: StateFlow<ForgotPasswordState> = _uiState.asStateFlow()

    fun submitEmail(email: String) {
        _uiState.value = ForgotPasswordState.EnterEmail(email = email, isLoading = true)
        viewModelScope.launch {
            try {
                val response = forgotPasswordUseCase(ForgotPasswordRequest(email, "12345"))
                if (response.success) {
                    _uiState.value = ForgotPasswordState.EnterOtp(email)
                } else {
                    _uiState.value = ForgotPasswordState.EnterEmail(error = response.message)
                }
            }catch(e: Exception) {

            }
        }
    }

    fun submitOtp(email: String, otp: String) {
        if (otp == generatedOtp) {
            _uiState.value = ForgotPasswordState.ConfirmReset(email, otp)
        } else {
            _uiState.value = ForgotPasswordState.EnterOtp(email, error = "Invalid OTP")
        }
    }

    fun confirmReset(email: String, otp: String) {
        _uiState.value = ForgotPasswordState.SetNewPassword(email, otp)
    }

    fun submitNewPassword(email: String, newPassword: String, confirmPassword: String) {
        viewModelScope.launch {
            if (newPassword != confirmPassword) {
                _uiState.value = ForgotPasswordState.SetNewPassword(email, generatedOtp, error = "Passwords do not match")
                return@launch
            }else {
                val response = resetPasswordUseCase(ResetPasswordRequest(email, generatedOtp, newPassword, confirmPassword))
                if (response.success) {
                    _uiState.value = ForgotPasswordState.ResetSuccess
                } else {
                    _uiState.value = ForgotPasswordState.SetNewPassword(email, generatedOtp, error = response.message)
                }
            }
        }
    }

    private fun generateOtp(): String {
        return (100000..999999).random().toString()
    }
}