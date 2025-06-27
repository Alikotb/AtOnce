package com.example.atonce.data.mappers

import com.example.atonce.data.remote.dto.OrderDetailsDto
import com.example.atonce.data.remote.dto.OrderDto
import com.example.atonce.data.remote.dto.OrdersResponse
import com.example.atonce.domain.entity.OrderEntity
import com.example.atonce.domain.entity.OrderResultEntity

fun OrdersResponse.toEntity(): OrderResultEntity {
    return result?.let { res ->
        OrderResultEntity(
            items = res.items.map { it.toEntity() },
            pageNumber = res.pageNumber,
            pageSize = res.pageSize,
            totalCount = res.totalCount,
            totalPages = res.totalPages
        )
    } ?: OrderResultEntity(
        items = emptyList(),
        pageNumber = 0,
        pageSize = 0,
        totalCount = 0,
        totalPages = 0
    )
}


fun OrderDto.toEntity(): OrderEntity {
    return OrderEntity(
        orderID = orderId.toString(),
        wareHouseName = wareHouseName,
        orderDate = createdAt,
        address = "",
        total = totalPrice,
        orderList = details.map { it.toEntity() }
    )
}

fun OrderDetailsDto.toEntity(): OrderEntity.OrderEntityItem {
    return OrderEntity.OrderEntityItem(
        medicineName = medicineName,
        medicineCount = quantity.toString(),
        medicinePrice = medicinePrice
    )
}
