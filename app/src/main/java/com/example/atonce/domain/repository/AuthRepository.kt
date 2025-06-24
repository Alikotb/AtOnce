package com.example.atonce.domain.repository

import com.example.atonce.data.remote.dto.AreaDto
import com.example.atonce.data.remote.dto.authentication.RegisterRequestDto
import com.example.atonce.data.remote.dto.authentication.RegisterResponseDto
import com.example.atonce.domain.entity.LoginResult
import com.example.atonce.domain.entity.Pharmacy
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<LoginResult>

    fun savePharmacy(pharmacy: Pharmacy)
    fun saveLanguage(code: String)
    fun getLanguage(): String

    fun getPharmacy(): Pharmacy

    suspend fun getAllGovernorates(): Flow<List<AreaDto>>

    suspend fun getAreasByGovernorateId(governorateId: Int): Flow<List<AreaDto>>

    suspend fun register(registerRequest: RegisterRequestDto): Flow<RegisterResponseDto>


    fun freePharmacy()

    fun isLoggedIn() : Boolean
}