package com.example.atonce.data.remote

import com.example.atonce.data.remote.dto.SupplierDto
import com.example.atonce.data.remote.dto.Warehouse.WarehouseDto
import com.example.atonce.data.remote.dto.Warehouse.WarehouseMedicinesDto
import com.example.atonce.data.remote.service.WarehouseApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RemoteDataSourceImpl(
    private val apiService: WarehouseApiService
) : RemoteDataSource {
    override suspend fun getAllWarehousesByArea(
        areaId: Int,
        page: Int,
        pageSize: Int,
        search: String
    ): Flow<List<WarehouseDto>> {
        return flowOf(apiService.getAllWarehousesByArea(areaId, page, pageSize, search).items)
    }
    override  suspend fun getAllMedicinesByWarehousesId(warehouseId: Int, pageNum: Int, pageSize: Int): Flow<WarehouseMedicinesDto>{
        return flowOf(apiService.getAllMedicinesByWarehousesId(warehouseId = warehouseId, pageNum = pageNum,pageSize=pageSize))
    }

    override suspend fun getAllSuppliersByAreaAndMedicine(
        areaId: Int,
        medicineId: Int
    ): Flow<List<SupplierDto>> {
       return flowOf(apiService.getAllSuppliersByAreaAndMedicine(areaId, medicineId))
    }

}


