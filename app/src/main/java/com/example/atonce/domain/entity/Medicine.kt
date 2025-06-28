package com.example.atonce.domain.entity

data class Medicine(
    val medicineId: Int,
    val medicineName: String,
    val arabicMedicineName : String,
    val drug: Int,
    val price: Double,
    val maximumwareHouseAreaName: String,
    val imageUrl: String,
    val finalPrice: Double,
    val totalQuantity: Int,
    val distributorsCount: Int,
    val warehouseIdOfMaxDiscount: Int,
    val warehouseNameOfMaxDiscount: String,
    val quantityInWarehouseWithMaxDiscount: Int,
    val maximumDiscount: Double,
    val minmumPrice: Double
)
