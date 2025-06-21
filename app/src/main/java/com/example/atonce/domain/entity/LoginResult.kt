package com.example.atonce.domain.entity

data class LoginResult(
    val token: String,
    val message: String,
    val success: Boolean,
    val pharmacy: Pharmacy
)

data class Pharmacy(
    val id: Int,
    val userName: String,
    val name: String,
    val email: String,
    val address: String,
    val governate: String,
    val areaId: Int,
    val phoneNumber: String
)

