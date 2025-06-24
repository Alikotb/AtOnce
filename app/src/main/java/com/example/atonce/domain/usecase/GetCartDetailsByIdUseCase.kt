package com.example.atonce.domain.usecase

import com.example.atonce.domain.repository.CartRepository

class GetCartDetailsByIdUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(pharmacyId: Int) = cartRepository.getCartItems(pharmacyId)
}