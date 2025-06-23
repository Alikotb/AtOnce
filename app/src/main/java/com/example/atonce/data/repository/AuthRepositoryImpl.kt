package com.example.atonce.data.repository

import com.example.atonce.data.local.sharedpreference.SharedPreferences
import com.example.atonce.data.mappers.toEntity
import com.example.atonce.data.remote.dto.AreaDto
import com.example.atonce.data.remote.dto.LoginRequestDto
import com.example.atonce.data.remote.dto.RegisterRequestDto
import com.example.atonce.data.remote.dto.RegisterResponseDto
import com.example.atonce.data.remote.service.AuthApiService
import com.example.atonce.domain.entity.LoginResult
import com.example.atonce.domain.entity.Pharmacy
import com.example.atonce.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class AuthRepositoryImpl(
    private val service: AuthApiService,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {
    override suspend fun login(email: String, password: String): Flow<LoginResult> {
        return flowOf(service.login(LoginRequestDto(email, password)).toEntity())
    }

    override fun savePharmacy(pharmacy: Pharmacy) {
        sharedPreferences.saveData("token", pharmacy.token)
        sharedPreferences.saveData("id", pharmacy.id)
        sharedPreferences.saveData("userName", pharmacy.userName)
        sharedPreferences.saveData("name", pharmacy.name)
        sharedPreferences.saveData("email", pharmacy.email)
        sharedPreferences.saveData("address", pharmacy.address)
        sharedPreferences.saveData("governate", pharmacy.governate)
        sharedPreferences.saveData("areaId", pharmacy.areaId)
        sharedPreferences.saveData("phoneNumber", pharmacy.phoneNumber)
    }

    override fun getPharmacy(): Pharmacy {
        return Pharmacy(
            sharedPreferences.fetchData("token", ""),
            sharedPreferences.fetchData("id", 0),
            sharedPreferences.fetchData("userName", ""),
            sharedPreferences.fetchData("name", ""),
            sharedPreferences.fetchData("email", ""),
            sharedPreferences.fetchData("address", ""),
            sharedPreferences.fetchData("governate", ""),
            sharedPreferences.fetchData("areaId", 0),
            sharedPreferences.fetchData("phoneNumber", ""),
        )
    }

    override suspend fun getAllGovernorates(): Flow<List<AreaDto>> {
        return  flowOf(service.getAllGovernorates())
    }

    override suspend fun getAreasByGovernorateId(governorateId: Int): Flow<List<AreaDto>> {
        return flowOf(service.getAreasByGovernorateId(governorateId))
    }

    override suspend fun register(registerRequest: RegisterRequestDto): Flow<RegisterResponseDto> {
        return flowOf(service.register(registerRequest))
    }
}