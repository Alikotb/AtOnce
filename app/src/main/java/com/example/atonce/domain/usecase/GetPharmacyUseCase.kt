package com.example.atonce.domain.usecase

import com.example.atonce.domain.entity.Pharmacy
import com.example.atonce.domain.repository.AuthRepository

class GetPharmacyUseCase(private val repository: AuthRepository) {
    operator fun invoke(): Pharmacy {
        return repository.getPharmacy()
    }
}