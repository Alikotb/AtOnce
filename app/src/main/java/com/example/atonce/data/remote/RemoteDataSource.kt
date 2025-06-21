package com.example.atonce.data.remote

import com.example.atonce.data.remote.dto.WarehouseDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getAllWarehousesByArea(areaId: Int, page: Int, pageSize: Int, search: String): Flow<List<WarehouseDto>>
}