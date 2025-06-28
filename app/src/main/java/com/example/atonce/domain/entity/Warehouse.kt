package com.example.atonce.domain.entity


data class Warehouse(
    val id: Int,
    val name: String,
    val address: String,
    val governate: String,
    val minmumPrice: Double,
    val imageUrl : String,
    val deliveryRate : String,
)

