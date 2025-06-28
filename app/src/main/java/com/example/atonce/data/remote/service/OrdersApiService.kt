package com.example.atonce.data.remote.service

import com.example.atonce.data.remote.dto.OrdersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OrdersApiService {
    @GET("api/order/getAllorderByStatus/{PharmacyID}")
    suspend fun getOrdersByStatus(
        @Path("PharmacyID") pharmacyID: Int,
        @Query("status") status: Int,
        @Query("page") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): OrdersResponse

}