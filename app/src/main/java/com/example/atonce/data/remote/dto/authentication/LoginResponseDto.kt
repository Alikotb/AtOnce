package com.example.atonce.data.remote.dto.authentication

data class LoginResponseDto(
    val token: String? = null,
    val message: String? = null,
    val success: Boolean? = null,
    val pharmacy: PharmacyDto? = null,
)

data class PharmacyDto(
    val token: String? = null,
    val id: Int? = null,
    val userName: String? = null,
    val name: String? = null,
    val email: String? = null,
    val address: String? = null,
    val governate: String? = null,
    val areaId: Int? = null,
    val phoneNumber: String? = null,
    val representativePhone: String? = "0.1515151"

)