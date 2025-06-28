package com.example.atonce.domain.usecase

import com.example.atonce.domain.entity.SupplierEntity
import com.example.atonce.domain.repository.WarehouseRepository
import kotlinx.coroutines.flow.Flow

class GetAllSuppliersByAreaIdAndMedicine(
    private val repository: WarehouseRepository
){

    suspend operator fun invoke(areaId: Int, medicineId: Int): Flow<List<SupplierEntity>> {
        return repository.getAllSuppliersByAreaAndMedicine(areaId, medicineId)
    }
}