package com.example.atonce.data.remote.service

import com.example.atonce.data.remote.dto.medicine.MedicineSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MedicineApiService {
    @GET("api/Medicine/SearchNameByAreaPagination/{areaId}")
    suspend fun searchMedicinesByNameAndArea(
        @Path("areaId") areaId: Int,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("search") search: String
    ) : MedicineSearchResponseDto
}