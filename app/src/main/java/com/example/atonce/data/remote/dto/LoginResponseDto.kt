package com.example.atonce.data.remote.dto

data class LoginResponseDto (
    val token : String,
    val message : String,
    val success : Boolean,
    val pharmacy : PharmacyDto
)

data class PharmacyDto (
    val id : Int,
    val userName : String,
    val name : String,
    val email : String,
    val address : String,
    val governate : String,
    val areaId : Int,
    val phoneNumber : String,
)