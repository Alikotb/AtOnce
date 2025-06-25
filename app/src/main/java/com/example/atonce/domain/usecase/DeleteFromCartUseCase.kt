package com.example.atonce.domain.usecase

import com.example.atonce.domain.entity.DeleteResponseEntity
import com.example.atonce.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class DeleteFromCartUseCase(
    private val repository: CartRepository
){
    suspend operator fun invoke(
        pharmacyId: Int,
        wareHouseId: Int,
        medicineId: Int
    ): Flow<DeleteResponseEntity> = repository.deleteFromCart(pharmacyId, wareHouseId, medicineId)
}