package com.example.atonce.data.remote.service

import com.example.atonce.data.remote.dto.Warehouse.WarehouseMedicinesDto
import com.example.atonce.data.remote.dto.Warehouse.WarehousesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WarehouseApiService {
    @GET("api/Warehouse/GetWarehousesByArea/{areaId}")
    suspend fun getAllWarehousesByArea(
        @Path("areaId") areaId: Int,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("search") search: String
        ): WarehousesResponse

    @GET("api/Warehouse/GetWarehousMedicines/{warehouseId}/medicines")
    suspend fun getAllMedicinesByWarehousesId(
        @Path("warehouseId") warehouseId: Int,
        @Query("page") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): WarehouseMedicinesDto
}