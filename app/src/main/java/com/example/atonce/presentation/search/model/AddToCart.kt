package com.example.atonce.presentation.search.model

import com.example.atonce.domain.entity.AddToCartParams

data class AddToCartUiModel(
    val warehouseId: Int,
    val pharmacyId: Int,
    val medicineId: Int,
    val quantity: Int
)

fun AddToCartUiModel.toEntity(): AddToCartParams {
    return AddToCartParams(
        warehouseId = this.warehouseId,
        pharmacyId = this.pharmacyId,
        medicineId = this.medicineId,
        englishMedicineName = "",
        arabicMedicineName = "",
        medicineUrl = "",
        warehouseUrl = "",
        price = 0.0,
        quantity = this.quantity,
        discount = 0.0
    )
}

fun AddToCartParams.toUiModel(): AddToCartUiModel {
    return AddToCartUiModel(
        warehouseId = this.warehouseId,
        pharmacyId = this.pharmacyId,
        medicineId = this.medicineId,
        quantity = this.quantity
    )
}
