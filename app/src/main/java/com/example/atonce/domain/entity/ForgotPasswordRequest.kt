package com.example.atonce.domain.entity

data class ForgotPasswordRequest(
    val email: String,
    val otp: String
)