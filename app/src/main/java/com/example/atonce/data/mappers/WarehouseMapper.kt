package com.example.atonce.data.mappers

import com.example.atonce.data.remote.dto.WarehouseDto
import com.example.atonce.domain.entity.Warehouse


fun WarehouseDto.toEntity() : Warehouse {
    return Warehouse(
        id = id,
        name = name,
        address = address,
        governate = governate,
        imageUrl = imageUrl ?: "https://www.reuters.com/resizer/v2/QOZLON6ZHRNQFO36VAFQCB2ZI4.jpg?auth=d82124e2af1cdaba30a06144e7637e34e99e770d04320ffe7b8a208eac3e379c&width=1080&quality=80",
        minmumPrice = minmumPrice,
        deliveryRate = deliveryRate ?: "Daily"
    )
}