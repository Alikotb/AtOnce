package com.example.atonce.data.remote

import android.util.Log
import com.example.atonce.data.remote.dto.WarehouseDto
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
        Log.d("TAG", "getAllWarehousesByArea: ${apiService.getAllWarehousesByArea(areaId, page, pageSize, search).items}")
        return flowOf(apiService.getAllWarehousesByArea(areaId, page, pageSize, search).items)
    }
}