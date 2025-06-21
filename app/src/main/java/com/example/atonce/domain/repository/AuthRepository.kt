package com.example.atonce.domain.repository

import com.example.atonce.domain.entity.LoginResult
import com.example.atonce.domain.entity.Pharmacy
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<LoginResult>

    fun savePharmacy(pharmacy: Pharmacy)

    fun getPharmacy(): Pharmacy
}