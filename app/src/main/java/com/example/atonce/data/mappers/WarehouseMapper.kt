package com.example.atonce.data.mappers

import com.example.atonce.data.remote.dto.WarehouseDto
import com.example.atonce.domain.entity.Warehouse


fun WarehouseDto.toEntity() : Warehouse {
    return Warehouse(
        id = id,
        name = name,
        address = address,
        governate = governate,
        imageUrl = imageUrl ?: "https://www.google.com/imgres?q=real%20madrid&imgurl=https%3A%2F%2Fwww.reuters.com%2Fresizer%2Fv2%2FQOZLON6ZHRNQFO36VAFQCB2ZI4.jpg%3Fauth%3Dd82124e2af1cdaba30a06144e7637e34e99e770d04320ffe7b8a208eac3e379c%26width%3D1080%26quality%3D80&imgrefurl=https%3A%2F%2Fwww.reuters.com%2Fsports%2Fsoccer%2Freal-madrid-beat-real-sociedad-bernabeu-bids-farewell-ancelotti-modric-2025-05-24%2F&docid=zOsAZM4IdDV3sM&tbnid=0-QOiY9YdxEkJM&vet=12ahUKEwid4tqL_YCOAxWhR_EDHS5VDYEQM3oFCIABEAA..i&w=1080&h=732&hcb=2&ved=2ahUKEwid4tqL_YCOAxWhR_EDHS5VDYEQM3oFCIABEAA",
        minmumPrice = minmumPrice,
        deliveryRate = deliveryRate
    )
}