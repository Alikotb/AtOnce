package com.example.atonce.data.remote.service

import com.example.atonce.data.remote.dto.cart.AddToCartRequestDto
import com.example.atonce.data.remote.dto.cart.AddToCartResponseDto
import com.example.atonce.data.remote.dto.cart.CartResponseDto
import com.example.atonce.data.remote.dto.cart.UpdateCartResponse
import com.example.atonce.data.remote.dto.cart.DeleteFromCartResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.POST
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


    @POST("api/Cart/add")
    suspend fun addToCart(
        @Body addToCartRequest: AddToCartRequestDto
    ): AddToCartResponseDto

    @DELETE("api/Cart/remove-item")
    suspend fun deleteFromCart(
        @Query("pharmacyId") pharmacyId: Int,
        @Query("warehouseId") warehouseId: Int,
        @Query("medicineId") medicineId: Int,
    ): DeleteFromCartResponse


}