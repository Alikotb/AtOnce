package com.example.atonce.presentation.orders.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.core.enums.ErrorMessagesEnum
import com.example.atonce.domain.Response
import com.example.atonce.domain.entity.OrderEntity
import com.example.atonce.domain.usecase.GetOrdersUseCase
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
    getPharmacyUseCase: GetPharmacyUseCase
) : ViewModel() {

    private val _orders = MutableStateFlow<Response<List<OrderEntity>>>(Response.Loading)
    val orders = _orders.asStateFlow()

    private var currentPage = 1
    private var totalPages = 1
    private var isLoading = false

    private val loadedOrders = mutableListOf<OrderEntity>()

    private val userData = getPharmacyUseCase()

    val errorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _orders.value = Response.Error(ErrorMessagesEnum.NETWORKERROR.getLocalizedMessage())
        isLoading = false
    }

    fun getOrdersByStatus(pharmacyID: Int = userData.id ?: 0, status: Int, pageSize: Int = 15) {
        if (isLoading || currentPage > totalPages) return

        isLoading = true
        _orders.value = if (currentPage == 1) Response.Loading else _orders.value

        viewModelScope.launch(Dispatchers.IO + errorExceptionHandler) {
            getOrdersUseCase(pharmacyID, status, currentPage, pageSize)
                .collect { response ->

                    totalPages = response.totalPages

                    if (currentPage == 1) {
                        loadedOrders.clear()
                    }

                    loadedOrders.addAll(response.items)

                    _orders.value = Response.Success(loadedOrders.toList())


                    currentPage++
                    isLoading = false
                }
        }
    }


    fun resetPagination() {
        currentPage = 1
        totalPages = 1
        loadedOrders.clear()
    }
}
