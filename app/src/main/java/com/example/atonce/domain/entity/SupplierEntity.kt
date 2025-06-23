package com.example.atonce.domain.entity


data class SupplierEntity(
    val discount: Double,
    val drug: Int,
    val finalPrice: Double,
    val medicineId: Int,
    val medicineName: String,
    val medicinePrice: Double,
    val quantity: Int,
    val wareHouseAreaName: String,
    val warehouseName: String,
    val warehouseId: Int,
    val warehouseImageUrl: String
)