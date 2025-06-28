package com.example.atonce.data.mappers

import com.example.atonce.data.remote.dto.SupplierDto
import com.example.atonce.domain.entity.SupplierEntity

fun SupplierDto.toEntity(): SupplierEntity {
    return SupplierEntity(
        discount = discount,
        drug = drug,
        finalPrice = finalPrice,
        medicineId = medicineId,
        medicineName = medicineName,
        medicinePrice = medicinePrice,
        quantity = quantity,
        wareHouseAreaName = wareHouseAreaName,
        warehouseName = warehHouseName,
        warehouseId = warehouseId,
        warehouseImageUrl = warehouseImageUrl
    )
}