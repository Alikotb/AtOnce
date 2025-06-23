package com.example.atonce.domain.usecase

import com.example.atonce.domain.entity.Pharmacy
import com.example.atonce.domain.repository.AuthRepository

class SaveLanguageUseCase(private val repository: AuthRepository) {
    operator fun invoke(code: String) {
        repository.saveLanguage(code)
    }
}