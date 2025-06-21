package com.example.atonce.domain.repository

import com.example.atonce.domain.entity.Warehouse
import kotlinx.coroutines.flow.Flow

interface WarehouseRepository {
    suspend fun getAllWarehousesByArea(areaId: Int, page: Int, pageSize: Int, search: String): Flow<List<Warehouse>>

}