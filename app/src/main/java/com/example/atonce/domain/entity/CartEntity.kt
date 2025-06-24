package com.example.atonce.domain.entity

data class CartEntity(
    val success: Boolean,
    val message: String,
    val totalQuantity: Int?,
    val totalPriceBeforeDiscount: Double?,
    val totalPriceAfterDiscount: Double?,
    val warehouses: List<CartWarehouseEntity>?
)

data class CartWarehouseEntity(
    val warehouseId: Int,
    val warehouseUrl: String?,
    val warehouseName: String?,
    val minimumPrice: Double?,
    val totalQuantity: Int?,
    val totalPriceBeforeDiscount: Double?,
    val totalPriceAfterDiscount: Double?,
    val items: List<CartItemEntity>
)

data class CartItemEntity(
    val medicineId: Int,
    val arabicMedicineName: String,
    val englishMedicineName: String,
    val medicineUrl: String,
    val quantity: Int,
    val priceAfterDiscount: Double,
    val priceBeforeDiscount: Double,
    val discount: Double
)
