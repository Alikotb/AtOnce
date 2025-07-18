package com.example.atonce.domain.repository

import com.example.atonce.data.remote.dto.Warehouse.WarehouseMedicinesDto
import com.example.atonce.domain.entity.SupplierEntity
import com.example.atonce.domain.entity.Warehouse
import kotlinx.coroutines.flow.Flow

interface WarehouseRepository {
    suspend fun getAllWarehousesByArea(areaId: Int, page: Int, pageSize: Int, search: String): Flow<List<Warehouse>>
    suspend fun getAllMedicinesByWarehousesId(warehouseId: Int, pageNum: Int, pageSize: Int,search: String,type: String): Flow<WarehouseMedicinesDto>
    suspend fun getAllSuppliersByAreaAndMedicine(areaId: Int, medicineId: Int): Flow<List<SupplierEntity>>
}