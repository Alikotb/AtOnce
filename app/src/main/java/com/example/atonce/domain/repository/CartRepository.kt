package com.example.atonce.domain.repository

import com.example.atonce.domain.entity.CartEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCartItems(pharmacyId: Int): Flow<CartEntity>
}