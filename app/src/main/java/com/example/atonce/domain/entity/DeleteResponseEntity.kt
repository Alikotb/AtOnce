package com.example.atonce.domain.entity

data class DeleteResponseEntity(
    val success: Boolean,
    val message: String,
    val data: Boolean
) {
}