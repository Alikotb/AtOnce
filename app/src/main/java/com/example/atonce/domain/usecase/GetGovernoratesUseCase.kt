package com.example.atonce.domain.usecase

import com.example.atonce.domain.repository.AuthRepository

class GetGovernoratesUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke() = repository.getAllGovernorates()
}