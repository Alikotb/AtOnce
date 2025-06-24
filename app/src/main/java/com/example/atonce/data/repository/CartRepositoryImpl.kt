package com.example.atonce.data.repository

import com.example.atonce.data.remote.dto.cart.CartWarehouseDto
import com.example.atonce.data.remote.dto.cart.UpdateCartResponse
import com.example.atonce.data.mappers.toEntity
import com.example.atonce.data.remote.service.CartApiService
import com.example.atonce.domain.entity.CartEntity
import com.example.atonce.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CartRepositoryImpl(
    private val apiService: CartApiService
) : CartRepository {

    override suspend fun getCartItems(pharmacyId: Int): Flow<List<CartWarehouseDto>> {
        return flowOf(apiService.getCartItems(pharmacyId).data.warehouses)
    }

    override suspend fun updateCart(
        pharmacyId: Int,
        warehouseId: Int,
        medicineId: Int,
        newQuantity: Int
    ): Flow<UpdateCartResponse> {
        return flowOf(apiService.updateCart(pharmacyId,warehouseId,medicineId,newQuantity))
    }
}