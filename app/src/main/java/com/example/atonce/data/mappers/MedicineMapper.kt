package com.example.atonce.data.mappers

import com.example.atonce.data.remote.dto.medicine.MedicineDto
import com.example.atonce.domain.entity.Medicine

fun MedicineDto.toEntity(): Medicine {
    return Medicine(
        medicineId = medicineId,
        medicineName = medicineName,
        drug = drug,
        price = price,
        maximumwareHouseAreaName = maximumwareHouseAreaName,
        imageUrl = "https://www.reuters.com/resizer/v2/QOZLON6ZHRNQFO36VAFQCB2ZI4.jpg?auth=d82124e2af1cdaba30a06144e7637e34e99e770d04320ffe7b8a208eac3e379c&width=1080&quality=80",
        finalPrice = finalPrice,
        totalQuantity = totalQuantity,
        distributorsCount = distributorsCount,
        warehouseIdOfMaxDiscount = warehouseIdOfMaxDiscount,
        warehouseNameOfMaxDiscount = warehouseNameOfMaxDiscount,
        quantityInWarehouseWithMaxDiscount = quantityInWarehouseWithMaxDiscount,
        maximumDiscount = maximumDiscount,
        minmumPrice = minmumPrice
    )
}