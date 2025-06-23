package com.example.atonce.domain.usecase

import com.example.atonce.domain.repository.AuthRepository

class IsLoggedInUseCase(private val repository: AuthRepository) {
    operator fun invoke(): Boolean {
        return repository.isLoggedIn()
    }
}