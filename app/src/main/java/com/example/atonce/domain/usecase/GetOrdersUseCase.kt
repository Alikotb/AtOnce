package com.example.atonce.domain.usecase

import com.example.atonce.domain.repository.OrdersRepository

class GetOrdersUseCase(
    private val ordersRepository: OrdersRepository
) {

    suspend operator fun invoke(
        pharmacyID: Int,
        status: Int,
        pageNumber: Int,
        pageSize: Int)
    = ordersRepository.getOrdersByStatus(pharmacyID, status, pageNumber, pageSize)

}