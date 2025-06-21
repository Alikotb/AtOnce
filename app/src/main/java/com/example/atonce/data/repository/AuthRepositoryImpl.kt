package com.example.atonce.data.repository

import com.example.atonce.data.mappers.toEntity
import com.example.atonce.data.remote.dto.LoginRequestDto
import com.example.atonce.data.remote.service.AuthApiService
import com.example.atonce.domain.entity.LoginResult
import com.example.atonce.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AuthRepositoryImpl(private val service: AuthApiService) : AuthRepository {
    override suspend fun login(email: String, password: String): Flow<LoginResult> {
        return flowOf(service.login(LoginRequestDto(email, password)).toEntity())
    }
}