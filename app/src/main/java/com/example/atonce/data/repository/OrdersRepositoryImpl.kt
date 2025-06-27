package com.example.atonce.data.repository

import com.example.atonce.data.mappers.toEntity
import com.example.atonce.data.remote.service.OrdersApiService
import com.example.atonce.domain.entity.OrderResultEntity
import com.example.atonce.domain.repository.OrdersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class OrdersRepositoryImpl(
    private val apiService: OrdersApiService
): OrdersRepository {
    override suspend fun getOrdersByStatus(
        pharmacyID: Int,
        status: Int,
        pageNumber: Int,
        pageSize: Int
    ): Flow<OrderResultEntity> {
        val response = apiService.getOrdersByStatus(pharmacyID, status, pageNumber, pageSize)
        return flowOf(response.toEntity())
    }
}