package com.example.atonce.data.remote.dto.medicine

data class MedicineSearchResponseDto(
    val items: List<MedicineDto>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int,
    val totalPages: Int
)

data class MedicineDto(
    val medicineId: Int,
    val medicineName: String,
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
