package com.example.atonce.domain.entity

data class OrderResultEntity(
    val items: List<OrderEntity>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int,
    val totalPages: Int
)

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
