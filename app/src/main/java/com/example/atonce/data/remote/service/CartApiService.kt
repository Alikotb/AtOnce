package com.example.atonce.data.remote.service

import com.example.atonce.data.remote.dto.cart.CartResponseDto
import com.example.atonce.data.remote.dto.cart.UpdateCartResponse
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface CartApiService {
    @GET("api/Cart/{pharmacyId}")
    suspend fun getCartItems(
        @Path("pharmacyId") pharmacyId: Int
    ): CartResponseDto

    @PUT("api/Cart/update-quantity")
    suspend fun updateCart(
        @Query("pharmacyId") pharmacyId: Int,
        @Query("warehouseId") warehouseId: Int,
        @Query("medicineId") medicineId: Int,
        @Query("newQuantity") newQuantity: Int,
        ):UpdateCartResponse

}