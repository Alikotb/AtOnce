package com.example.atonce.data.remote.dto


data class SupplierDto(
    val discount: Double,
    val drug: Int,
    val finalPrice: Double,
    val medicineId: Int,
    val medicineName: String,
    val medicinePrice: Double,
    val quantity: Int,
    val wareHouseAreaName: String,
    val warehHouseName: String,
    val warehouseId: Int,
    val warehouseImageUrl: String
)