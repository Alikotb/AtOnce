package com.example.atonce.data.remote.dto

data class RegisterRequestDto(
    val address: String,
    val areaId: Int,
    val confirmPassword: String,
    val email: String,
    val governate: String,
    val name: String,
    val password: String,
    val phoneNumber: String,
    val representativeCode: String,
    val userName: String
)