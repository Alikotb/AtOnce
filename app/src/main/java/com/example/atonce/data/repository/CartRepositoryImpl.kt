package com.example.atonce.data.repository

import com.example.atonce.data.remote.service.CartApiService
import com.example.atonce.domain.repository.CartRepository
import com.example.atonce.presentation.cart.CartItem
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val apiService: CartApiService
) : CartRepository {

    override suspend fun getCartItems(pharmacyId: Int): Flow<List<CartItem>> {
        TODO("Not yet implemented")
    }
}