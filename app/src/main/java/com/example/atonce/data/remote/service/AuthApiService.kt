package com.example.atonce.data.remote.service

import com.example.atonce.data.remote.dto.AreaDto
import com.example.atonce.data.remote.dto.authentication.ResetPasswordRequestDto
import com.example.atonce.data.remote.dto.authentication.ForgotPasswordRequestDto
import com.example.atonce.data.remote.dto.authentication.ForgotPasswordResponseDto
import com.example.atonce.data.remote.dto.authentication.RegisterRequestDto
import com.example.atonce.data.remote.dto.authentication.RegisterResponseDto

import com.example.atonce.data.remote.dto.authentication.LoginRequestDto
import com.example.atonce.data.remote.dto.authentication.LoginResponseDto
import com.example.atonce.data.remote.dto.authentication.ResetPasswordResponseDto
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

    @POST("api/Pharmacy/forgot-password")
    suspend fun forgotPassword(@Body forgotPasswordRequestDto: ForgotPasswordRequestDto): ForgotPasswordResponseDto

    @POST("api/Pharmacy/reset-password")
    suspend fun resetPassword(@Body resetPasswordRequestDto: ResetPasswordRequestDto): ResetPasswordResponseDto

}