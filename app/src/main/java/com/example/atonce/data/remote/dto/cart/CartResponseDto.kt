package com.example.atonce.data.remote.dto.cart

data class CartResponseDto(
    val success: Boolean,
    val message: String,
    val data: CartDataDto?
)

data class CartDataDto(
    val totalQuantity: Int,
    val totalPriceBeforeDisscount: Double,
    val totalPriceAfterDisscount: Double,
    val warehouses: List<CartWarehouseDto>
)

data class CartWarehouseDto(
    val warehouseId: Int,
    val warehouseUrl: String?,
    val items: List<CartItemDto>
)

data class CartItemDto(
    val medicineId: Int,
    val arabicMedicineName: String,
    val englishMedicineName: String,
    val medicineUrl: String,
    val quantity: Int,
    val priceAfterDiscount: Double,
    val priceBeforeDiscount: Double,
    val discount: Double
)
