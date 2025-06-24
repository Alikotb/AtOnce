package com.example.atonce.data.remote.dto.cart

data class AddToCartRequestDto(
    val warehouseId: Int,
    val pharmacyId: Int,
    val medicineId: Int,
    val englishMedicineName: String,
    val arabicMedicineName: String,
    val medicineUrl: String,
    val warehouseUrl: String,
    val price: Double,
    val quantity: Int,
    val discount: Double
)
