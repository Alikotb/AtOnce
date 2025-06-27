package com.example.atonce.presentation.orders.model

data class OrderModel(
    val orderID: String,
    val wareHouseName: String,
    val orderDate: String,
    val address: String,
    val total: Double,
    val orderList : List<OrderItemModel>
) {
    data class OrderItemModel(
        val medicineName : String,
        val medicineCount: String,
        val medicinePrice: Double
    )
}
