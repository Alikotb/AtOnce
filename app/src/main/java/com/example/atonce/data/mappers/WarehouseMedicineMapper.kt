package com.example.atonce.data.mappers

import com.example.atonce.data.remote.dto.WarehouseMedicinesDto.WarehouseMedicinesItemDto
import com.example.atonce.presentation.store.model.WarehouseMedicines

fun WarehouseMedicinesItemDto.toEntity(): WarehouseMedicines{
    return WarehouseMedicines(
        medicineImage = "",
        medicineName = medicineName,
        medicineDiscount = discount,
        medicineQuantity = quantity,
        medicinePrice = 10.00,
        medicineFinalPrice = 20.00,

    )
}
