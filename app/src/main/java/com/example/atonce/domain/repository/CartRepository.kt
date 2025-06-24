package com.example.atonce.domain.repository

import com.example.atonce.data.remote.dto.cart.CartWarehouseDto
import com.example.atonce.presentation.cart.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCartItems(pharmacyId: Int): Flow<List<CartWarehouseDto>>

}