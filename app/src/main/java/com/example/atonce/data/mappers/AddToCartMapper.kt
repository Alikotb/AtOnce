package com.example.atonce.data.mappers

import com.example.atonce.data.remote.dto.cart.AddToCartRequestDto
import com.example.atonce.data.remote.dto.cart.AddToCartResponseDto
import com.example.atonce.domain.entity.AddToCartParams
import com.example.atonce.domain.entity.AddToCartResult

fun AddToCartRequestDto.toEntity(): AddToCartParams {
    return AddToCartParams(
        warehouseId = warehouseId,
        pharmacyId = pharmacyId,
        medicineId = medicineId,
        englishMedicineName = englishMedicineName,
        arabicMedicineName = arabicMedicineName,
        medicineUrl = medicineUrl,
        warehouseUrl = warehouseUrl,
        price = price,
        quantity = quantity,
        discount = discount
    )
}

fun AddToCartParams.toDto(): AddToCartRequestDto {
    return AddToCartRequestDto(
        warehouseId = warehouseId,
        pharmacyId = pharmacyId,
        medicineId = medicineId,
        englishMedicineName = englishMedicineName,
        arabicMedicineName = arabicMedicineName,
        medicineUrl = medicineUrl,
        warehouseUrl = warehouseUrl,
        price = price,
        quantity = quantity,
        discount = discount
    )
}

fun AddToCartResponseDto.toEntity(): AddToCartResult {
    return AddToCartResult(
        isSuccessful = success,
        message = message
    )
}