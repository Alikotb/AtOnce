package com.example.atonce.domain.repository

import com.example.atonce.domain.entity.LoginResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<LoginResult>

}