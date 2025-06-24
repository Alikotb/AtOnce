package com.example.atonce.domain.repository

import com.example.atonce.data.remote.dto.cart.UpdateCartResponse
import com.example.atonce.domain.entity.CartEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun updateCart(pharmacyId: Int,warehouseId: Int,medicineId: Int,newQuantity: Int): Flow<UpdateCartResponse>

    suspend fun getCartItems(pharmacyId: Int): Flow<CartEntity>
}

