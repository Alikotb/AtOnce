package com.example.atonce.data.remote.service

import com.example.atonce.data.remote.dto.authentication.LoginRequestDto
import com.example.atonce.data.remote.dto.authentication.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/Pharmacy/login")
    suspend fun login(@Body loginRequestDto: LoginRequestDto): LoginResponseDto
}