package com.example.atonce.domain.entity

data class PharmacyEntity(
    val token: String,
    val id: Int,
    val userName: String,
    val pharmacyName: String,
    val email: String,
    val address: String,
    val governorate: String,
    val areaId: Int,
    val phoneNumber: String
)

