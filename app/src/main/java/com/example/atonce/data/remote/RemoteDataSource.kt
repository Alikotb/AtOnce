package com.example.atonce.data.remote

import com.example.atonce.data.remote.dto.SupplierDto
import com.example.atonce.data.remote.dto.Warehouse.WarehouseDto
import com.example.atonce.data.remote.dto.Warehouse.WarehouseMedicinesDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getAllWarehousesByArea(areaId: Int, page: Int, pageSize: Int, search: String): Flow<List<WarehouseDto>>
    suspend fun getAllMedicinesByWarehousesId(warehouseId: Int, pageNum: Int, pageSize: Int,search: String): Flow<WarehouseMedicinesDto>
    suspend fun getAllSuppliersByAreaAndMedicine(areaId: Int, medicineId: Int): Flow<List<SupplierDto>>
}

