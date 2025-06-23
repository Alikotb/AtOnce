package com.example.atonce.domain.usecase

import com.example.atonce.domain.entity.Pharmacy
import com.example.atonce.domain.repository.AuthRepository

class GetLanguageUseCase(private val repository: AuthRepository) {
    operator fun invoke(): String {
        return repository.getLanguage()
    }
}