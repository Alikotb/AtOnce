package com.example.atonce.domain.entity


data class OrderEntity(
    val orderID: String,
    val wareHouseName: String,
    val orderDate: String,
    val address: String,
    val total: Double,
    val orderList : List<OrderEntityItem>
) {
    data class OrderEntityItem(
        val medicineName : String,
        val medicineCount: String,
        val medicinePrice: Double
    )
}
