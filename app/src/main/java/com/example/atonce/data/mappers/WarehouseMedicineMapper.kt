package com.example.atonce.data.mappers

import com.example.atonce.data.remote.dto.Warehouse.WarehouseMedicinesDto.WarehouseMedicinesItemDto
import com.example.atonce.presentation.store.model.WarehouseMedicines

fun WarehouseMedicinesItemDto.toEntity(): WarehouseMedicines {
    return WarehouseMedicines(
        medicineId = medicineId,
        arabicMedicineName = arabicMedicineName,
        type = drug,
        englishMedicineName =englishMedicineName,
        medicineImage = medicineUrl,
        medicineDiscount =discount,
        medicinePrice =price,
        medicineFinalPrice=finalprice,
        medicineQuantity =quantity
    )
}
