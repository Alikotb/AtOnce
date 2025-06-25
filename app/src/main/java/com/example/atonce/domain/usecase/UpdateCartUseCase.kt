package com.example.atonce.domain.usecase

import com.example.atonce.data.remote.dto.cart.UpdateCartRequest
import com.example.atonce.data.remote.dto.cart.UpdateCartResponse
import com.example.atonce.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class UpdateCartUseCase(private val repo : CartRepository) {
    suspend operator fun invoke(pharmacyId:Int,request: UpdateCartRequest): Flow<UpdateCartResponse>{
        return repo.updateCart(pharmacyId,request.warehouseId,request.medicineId,request.newQuantity)
    }
}