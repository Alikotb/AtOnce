package com.example.atonce.data.remote.dto

data class WarehouseMedicinesDto(
    val items: List<WarehouseMedicinesItemDto>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int,
    val totalPages: Int
){
    data class WarehouseMedicinesItemDto(
        val arabicMedicineName: String,
        val discount: Double,
        val drug: Int,
        val englishMedicineName: String,
        val finalprice: Double,
        val medicineId: Int,
        val medicineUrl: String,
        val price: Double,
        val quantity: Int
    )
}



