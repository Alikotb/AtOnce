package com.example.atonce.presentation.store.model

data class WarehouseMedicines(
    val medicineId: Int,
    val arabicMedicineName: String,
    val type: Int,
    val englishMedicineName: String,
    val medicineImage : String,
    val medicineDiscount : Double,
    val medicinePrice : Double,
    val medicineFinalPrice:Double,
    val medicineQuantity : Int
)
