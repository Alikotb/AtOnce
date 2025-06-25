package com.example.atonce.data.repository

import com.example.atonce.data.mappers.toDto
import com.example.atonce.data.mappers.toEntity
import com.example.atonce.data.remote.dto.cart.AddToCartRequestDto
import com.example.atonce.data.remote.service.CartApiService
import com.example.atonce.domain.entity.AddToCartParams
import com.example.atonce.domain.entity.AddToCartResult
import com.example.atonce.domain.entity.CartEntity
import com.example.atonce.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CartRepositoryImpl(
    private val apiService: CartApiService
) : CartRepository {
    override suspend fun getCartItems(pharmacyId: Int): Flow<CartEntity> {
        return flowOf(apiService.getCartItems(pharmacyId).toEntity())
    }


    override suspend fun addToCart(cartRequest: AddToCartParams): AddToCartResult {
        return apiService.addToCart(cartRequest.toDto()).toEntity()
    }
}