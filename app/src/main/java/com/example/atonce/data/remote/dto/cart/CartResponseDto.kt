package com.example.atonce.data.remote.dto.cart

data class CartResponseDto(
    val items: List<CartItemDto>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int,
    val totalPages: Int
)

data class CartItemDto(
    val id: Int,
    val quantity: Int,
    val totalPrice: Double
)