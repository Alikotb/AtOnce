package com.example.atonce.domain.usecase

import com.example.atonce.data.remote.dto.authentication.RegisterRequestDto
import com.example.atonce.data.remote.dto.authentication.RegisterResponseDto
import com.example.atonce.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(private val authRepository: AuthRepository){
    suspend operator fun invoke(registerRequest: RegisterRequestDto): Flow<RegisterResponseDto> {
        return authRepository.register(registerRequest)
    }

}