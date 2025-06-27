package com.example.atonce.domain.entity

sealed class ForgotPasswordState {
    data class EnterEmail(
        val email: String = "",
        val isLoading: Boolean = false,
        val error: String? = null
    ) : ForgotPasswordState()

    data class EnterOtp(
        val email: String,
        val otp: String = "",
        val isLoading: Boolean = false,
        val error: String? = null
    ) : ForgotPasswordState()

    data class ConfirmReset(
        val email: String,
        val otp: String,
        val isLoading: Boolean = false
    ) : ForgotPasswordState()

    data class SetNewPassword(
        val email: String,
        val otp: String,
        val newPassword: String = "",
        val confirmPassword: String = "",
        val isLoading: Boolean = false,
        val error: String? = null
    ) : ForgotPasswordState()

    object ResetSuccess : ForgotPasswordState()
}