package com.example.atonce.domain.entity

data class ResetPasswordRequest(
    val email: String,
    val otp: String,
    val newPassword: String,
    val confirmPassword: String
)
