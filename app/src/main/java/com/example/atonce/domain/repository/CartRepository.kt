package com.example.atonce.domain.repository

import com.example.atonce.data.remote.dto.cart.CartWarehouseDto
import com.example.atonce.data.remote.dto.cart.UpdateCartResponse
import com.example.atonce.presentation.cart.CartItem
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface CartRepository {
    suspend fun getCartItems(pharmacyId: Int): Flow<List<CartWarehouseDto>>
    suspend fun updateCart(pharmacyId: Int,warehouseId: Int,medicineId: Int,newQuantity: Int): Flow<UpdateCartResponse>

}

