package com.example.atonce.domain.usecase

import com.example.atonce.domain.entity.AddToCartParams
import com.example.atonce.domain.entity.AddToCartResult
import com.example.atonce.domain.repository.CartRepository

class AddToCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(cartRequest: AddToCartParams): AddToCartResult {
        return cartRepository.addToCart(cartRequest)
    }
}