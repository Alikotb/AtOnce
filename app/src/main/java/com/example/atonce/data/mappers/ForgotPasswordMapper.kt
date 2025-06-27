package com.example.atonce.data.mappers

import com.example.atonce.data.remote.dto.authentication.ForgotPasswordRequestDto
import com.example.atonce.data.remote.dto.authentication.ForgotPasswordResponseDto
import com.example.atonce.data.remote.dto.authentication.ResetPasswordRequestDto
import com.example.atonce.data.remote.dto.authentication.ResetPasswordResponseDto
import com.example.atonce.domain.entity.ForgotPasswordRequest
import com.example.atonce.domain.entity.ForgotPasswordResponse
import com.example.atonce.domain.entity.ResetPasswordRequest
import com.example.atonce.domain.entity.ResetPasswordResponse

fun ForgotPasswordRequestDto.toEntity(): ForgotPasswordRequest {
    return ForgotPasswordRequest(
        email = this.email,
        otp = this.otp
    )
}

fun ForgotPasswordResponseDto.toEntity(): ForgotPasswordResponse {
    return ForgotPasswordResponse(
        message = this.message,
        success = this.success
    )
}

fun ForgotPasswordRequest.toDto(): ForgotPasswordRequestDto {
    return ForgotPasswordRequestDto(
        email = this.email,
        otp = this.otp
    )
}

fun ResetPasswordRequestDto.toEntity(): ResetPasswordRequest {
    return ResetPasswordRequest(
        email = this.email,
        otp = this.otp,
        newPassword = this.newPassword,
        confirmPassword = this.confirmPassword
    )
}

fun ResetPasswordResponseDto.toEntity(): ResetPasswordResponse {
    return ResetPasswordResponse(
        message = this.message,
        success = this.success
    )
}

fun ResetPasswordRequest.toDto(): ResetPasswordRequestDto {
    return ResetPasswordRequestDto(
        email = this.email,
        otp = this.otp,
        newPassword = this.newPassword,
        confirmPassword = this.confirmPassword
    )
}