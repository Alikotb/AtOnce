package com.example.atonce.domain.repository

import com.example.atonce.domain.entity.OrderResultEntity
import kotlinx.coroutines.flow.Flow

interface OrdersRepository {

    suspend fun getOrdersByStatus(pharmacyID: Int, status: Int, pageNumber: Int, pageSize: Int): Flow<OrderResultEntity>
}