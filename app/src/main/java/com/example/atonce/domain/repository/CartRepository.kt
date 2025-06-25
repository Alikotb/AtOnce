package com.example.atonce.domain.repository

import com.example.atonce.domain.entity.AddToCartParams
import com.example.atonce.domain.entity.AddToCartResult
import com.example.atonce.domain.entity.CartEntity
import com.example.atonce.domain.entity.DeleteResponseEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCartItems(pharmacyId: Int): Flow<CartEntity>
    suspend fun addToCart(cartRequest: AddToCartParams): AddToCartResult
    suspend fun deleteFromCart(pharmacyId: Int,wareHouseId: Int, medicineId: Int): Flow<DeleteResponseEntity>
}