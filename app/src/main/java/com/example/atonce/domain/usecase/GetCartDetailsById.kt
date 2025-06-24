package com.example.atonce.domain.usecase

import com.example.atonce.domain.repository.CartRepository

class GetCartDetailsById(private val cartRepository: CartRepository) {
    suspend operator fun invoke(pharmacyId: Int) = cartRepository.getCartItems(pharmacyId)
}