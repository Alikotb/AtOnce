package com.example.atonce.domain.usecase

import com.example.atonce.domain.entity.ResetPasswordRequest
import com.example.atonce.domain.entity.ResetPasswordResponse
import com.example.atonce.domain.repository.AuthRepository

class ResetPasswordUseCase(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(request: ResetPasswordRequest) : ResetPasswordResponse {
        return repo.resetPassword(request)
    }
}