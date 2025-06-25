package com.example.atonce.data.mappers

import com.example.atonce.data.remote.dto.cart.CartItemDto
import com.example.atonce.data.remote.dto.cart.CartResponseDto
import com.example.atonce.data.remote.dto.cart.CartWarehouseDto
import com.example.atonce.data.remote.dto.cart.DeleteFromCartResponse
import com.example.atonce.domain.entity.CartEntity
import com.example.atonce.domain.entity.CartItemEntity
import com.example.atonce.domain.entity.CartWarehouseEntity
import com.example.atonce.domain.entity.DeleteResponseEntity

fun CartResponseDto.toEntity(): CartEntity {
    return CartEntity(
        success = success,
        message = message,
        totalQuantity = data?.totalQuantity,
        totalPriceBeforeDiscount = data?.totalPriceBeforeDisscount,
        totalPriceAfterDiscount = data?.totalPriceAfterDisscount,
        warehouses = data?.warehouses?.map { it.toEntity() }
    )
}

fun CartWarehouseDto.toEntity(): CartWarehouseEntity {
    return CartWarehouseEntity(
        warehouseId = warehouseId,
        warehouseUrl = warehouseUrl,
        warehouseName = name,
        minimumPrice = minWarehousePriceInPharmacyArea,
        totalQuantity = items.sumOf { it.quantity },
        totalPriceBeforeDiscount = items.sumOf { it.priceBeforeDiscount * it.quantity } ,
        totalPriceAfterDiscount = items.sumOf { it.priceAfterDiscount * it.quantity } ,
        items = items.map { it.toEntity() }
    )
}

fun CartItemDto.toEntity(): CartItemEntity {
    return CartItemEntity(
        medicineId = medicineId,
        arabicMedicineName = arabicMedicineName,
        englishMedicineName = englishMedicineName,
        medicineUrl = medicineUrl,
        quantity = quantity,
        priceAfterDiscount = priceAfterDiscount,
        priceBeforeDiscount = priceBeforeDiscount,
        discount = discount
    )
}

fun DeleteFromCartResponse.toEntity(): DeleteResponseEntity {
    return DeleteResponseEntity(
        success = success,
        message = message,
        data = data
    )
}
