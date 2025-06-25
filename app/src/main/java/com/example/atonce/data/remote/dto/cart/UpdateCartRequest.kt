package com.example.atonce.data.remote.dto.cart

import retrofit2.http.Query

data class UpdateCartRequest(
    val warehouseId: Int,
    val medicineId: Int,
    val newQuantity: Int,
)
