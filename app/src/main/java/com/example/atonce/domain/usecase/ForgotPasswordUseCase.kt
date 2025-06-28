package com.example.atonce.domain.usecase

import com.example.atonce.domain.entity.ForgotPasswordRequest
import com.example.atonce.domain.entity.ForgotPasswordResponse
import com.example.atonce.domain.repository.AuthRepository

class ForgotPasswordUseCase(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(request: ForgotPasswordRequest) : ForgotPasswordResponse {
        return repo.forgotPassword(request)
    }
}