package com.example.atonce.data.remote.service

import com.example.atonce.data.remote.dto.AreaDto
import com.example.atonce.data.remote.dto.RegisterRequestDto
import com.example.atonce.data.remote.dto.RegisterResponseDto

import com.example.atonce.data.remote.dto.authentication.LoginRequestDto
import com.example.atonce.data.remote.dto.authentication.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {
    @POST("api/Pharmacy/login")
    suspend fun login(@Body loginRequestDto: LoginRequestDto): LoginResponseDto

    @GET("api/Pharmacy/register")
    suspend fun getAllGovernorates(): List<AreaDto>

    @GET("api/Pharmacy/register")
    suspend fun getAreasByGovernorateId(@Query("governateId") governorateId: Int): List<AreaDto>

    @POST("api/Pharmacy/register")
    suspend fun register(@Body registerRequestDto: RegisterRequestDto): RegisterResponseDto
}