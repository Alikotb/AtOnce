package com.example.atonce.data.repository

import com.example.atonce.data.mappers.toEntity
import com.example.atonce.data.remote.RemoteDataSource
import com.example.atonce.data.remote.dto.Warehouse.WarehouseMedicinesDto
import com.example.atonce.domain.entity.Warehouse
import com.example.atonce.domain.repository.WarehouseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WarehouseRepositoryImpl(private val remoteDataSource: RemoteDataSource) : WarehouseRepository {
    override suspend fun getAllWarehousesByArea(
        areaId: Int,
        page: Int,
        pageSize: Int,
        search: String
    ): Flow<List<Warehouse>> {
        return remoteDataSource.getAllWarehousesByArea(areaId, page, pageSize, search).map{ list -> list.map { it.toEntity() } }
    }
    override  suspend fun getAllMedicinesByWarehousesId(warehouseId: Int, pageNum: Int, pageSize: Int): Flow<WarehouseMedicinesDto>{
        return remoteDataSource.getAllMedicinesByWarehousesId(warehouseId,pageNum,pageSize)
    }

}