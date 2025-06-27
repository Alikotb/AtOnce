package com.example.atonce.data.remote.dto.authentication

data class ForgotPasswordRequestDto(
    val email: String,
    val otp: String
)