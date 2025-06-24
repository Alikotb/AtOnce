package com.example.atonce.data.remote.service

import com.example.atonce.data.remote.dto.cart.CartResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CartApiService {
    @GET("api/Cart/{pharmacyId}")
    suspend fun getCartItems(
        @Path("pharmacyId") pharmacyId: Int
    ): CartResponseDto
}