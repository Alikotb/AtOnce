package com.example.atonce.presentation.store.model

data class WarehouseMedicines(
    val medicineImage : String,
    val medicineName: String,
    val medicineDiscount : Double,
    val medicinePrice : Double,
    val medicineFinalPrice:Double,
    val medicineQuantity : Int
)