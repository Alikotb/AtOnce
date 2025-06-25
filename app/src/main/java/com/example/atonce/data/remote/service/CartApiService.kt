package com.example.atonce.data.remote.service

import com.example.atonce.data.remote.dto.cart.AddToCartRequestDto
import com.example.atonce.data.remote.dto.cart.AddToCartResponseDto
import com.example.atonce.data.remote.dto.cart.CartResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApiService {
    @GET("api/Cart/{pharmacyId}")
    suspend fun getCartItems(
        @Path("pharmacyId") pharmacyId: Int
    ): CartResponseDto

    @POST("api/Cart/add")
    suspend fun addToCart(
        @Body addToCartRequest: AddToCartRequestDto
    ): AddToCartResponseDto

}