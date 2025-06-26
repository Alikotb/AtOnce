package com.example.atonce.domain.usecase

import com.example.atonce.domain.repository.CartRepository

class PlaceOrderUseCase(private val repository: CartRepository ) {
    suspend operator fun invoke(warehouseID:Int,pharmacyID:Int)=repository.placeOrder(pharmacyID,warehouseID)
}