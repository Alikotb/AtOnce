package com.example.atonce.domain.usecase

import com.example.atonce.domain.repository.WarehouseRepository

class GetAllMedicinesByWarehousesId(private val repo: WarehouseRepository)  {
    suspend operator fun invoke(warehouseId: Int, pageNum: Int, pageSize: Int) =
        repo.getAllMedicinesByWarehousesId(warehouseId,pageNum,pageSize)
}