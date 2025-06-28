package com.example.atonce.domain.usecase

import com.example.atonce.domain.repository.AuthRepository

class GetAreasUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(
        governorateId : Int
    ) = repository.getAreasByGovernorateId(governorateId = governorateId)
}