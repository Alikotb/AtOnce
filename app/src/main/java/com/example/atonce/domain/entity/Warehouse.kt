package com.example.atonce.domain.entity

import androidx.compose.ui.graphics.vector.ImageVector

data class Warehouse(
    val id: Int,
    val name: String,
    val address: String,
    val governate: String,
    val minmumPrice: Double,
    val imageUrl : String,
    val deliveryRate : String,
)