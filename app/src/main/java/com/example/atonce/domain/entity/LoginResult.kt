package com.example.atonce.domain.entity

data class LoginResult(
    val token: String? = null,
    val message: String? = null,
    val success: Boolean? = null,
    val pharmacy: Pharmacy? = null,
    val errors: LoginErrors? = null
)

data class Pharmacy(
    val token: String? = null,
    val id: Int? = null,
    val userName: String? = null,
    val name: String? = null,
    val email: String? = null,
    val address: String? = null,
    val governate: String? = null,
    val areaId: Int? = null,
    val phoneNumber: String? = null,
    val representativePhone: String? = null
)


data class LoginErrors(
    val Password: List<String>? = null,
    val Email: List<String>? = null
)

