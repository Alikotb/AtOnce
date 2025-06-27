package com.example.atonce.data.remote.dto.authentication

data class ResetPasswordRequestDto(
    val email: String,
    val otp: String,
    val newPassword: String,
    val confirmPassword: String
)
