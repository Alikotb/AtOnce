package com.example.atonce.data.remote.dto

import androidx.compose.ui.graphics.vector.ImageVector

data class WarehouseDto(
    val id: Int,
    val name: String,
    val address: String,
    val governate: String,
    val minmumPrice: Double,
    val imageUrl : String?,
    val deliveryRate : String,
)