package com.example.atonce.presentation.home.model

import com.example.atonce.R
import com.example.atonce.domain.entity.Warehouse

data class WarehouseUiModel(
    val name: String,
    val location: String,
    val minOrder: Int,
    val rating: Int,
    val imageUrl: String
)

fun Warehouse.toUiModel(): WarehouseUiModel {
    return WarehouseUiModel(
        name = this.name,
        location = this.address,
        minOrder = this.minmumPrice.toInt(),
        rating = 4,
        imageUrl = this.imageUrl
    )
}