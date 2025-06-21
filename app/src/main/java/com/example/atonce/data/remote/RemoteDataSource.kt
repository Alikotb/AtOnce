package com.example.atonce.data.remote

import com.example.atonce.data.remote.dto.WarehouseDto
import com.example.atonce.data.remote.dto.WarehouseMedicinesDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getAllWarehousesByArea(areaId: Int, page: Int, pageSize: Int, search: String): Flow<List<WarehouseDto>>
    suspend fun getAllMedicinesByWarehousesId(warehouseId: Int, pageNum: Int, pageSize: Int): Flow<WarehouseMedicinesDto>

}

