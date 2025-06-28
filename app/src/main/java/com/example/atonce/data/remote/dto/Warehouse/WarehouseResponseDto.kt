package com.example.atonce.data.remote.dto.Warehouse


data class WarehouseDto(
    val id: Int,
    val name: String,
    val address: String,
    val governate: String,
    val minmumPrice: Double,
    val imageUrl : String?,
    val deliveryRate : String?,
)

data class WarehousesResponse(
    val items: List<WarehouseDto>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int,
    val totalPages: Int
)