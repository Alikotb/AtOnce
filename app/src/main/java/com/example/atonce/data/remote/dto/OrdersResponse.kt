package com.example.atonce.data.remote.dto

data class OrdersResponse(
    val message: String,
    val result: Result?
)

data class Result(
    val items: List<OrderDto>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int,
    val totalPages: Int
)

data class OrderDto(
    val createdAt: String,
    val details: List<OrderDetailsDto>,
    val orderId: Int,
    val quantity: Int,
    val status: String,
    val totalPrice: Double,
    val wareHouseImage: String,
    val wareHouseName: String
)

data class OrderDetailsDto(
    val arabicMedicineName: String,
    val discountAmount: Double,
    val discountPercentage: Double,
    val medicineImage: String,
    val medicineName: String,
    val medicinePrice: Double,
    val quantity: Int,
    val totalPriceAfterDisccount: Double,
    val totalPriceBeforeDisccount: Double
)