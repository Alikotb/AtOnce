package com.example.atonce.presentation.store.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.data.mappers.toEntity
import com.example.atonce.data.remote.Response
import com.example.atonce.data.remote.dto.Warehouse.WarehouseMedicinesDto
import com.example.atonce.domain.usecase.GetAllMedicinesByWarehousesId
import com.example.atonce.domain.usecase.SearchInWareHouseUseCase
import com.example.atonce.presentation.store.model.WarehouseMedicines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WarehouseViewModel(private val getAllMedicinesByWarehousesId: GetAllMedicinesByWarehousesId,
                         private val searchUseCase: SearchInWareHouseUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow<Response<List<WarehouseMedicines>>>(Response.Loading)
    val uiState = _uiState.asStateFlow()
    private val fullList = mutableListOf<WarehouseMedicines>()

    private var currentPage = 1
    private var pageSize = 10
    private var isLastPage = false
    private var isLoading = false

    fun getAllMedicinesByStoreId(warehouseId: Int) {

        if (isLoading || isLastPage) return
        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            getAllMedicinesByWarehousesId(
                warehouseId = warehouseId,
                pageNum = currentPage,
                pageSize = pageSize
            )
                .catch {
                    _uiState.value = Response.Error("")
                    isLoading = false
                }.collect { result: WarehouseMedicinesDto ->
                    val items = result.items.map { it.toEntity() }
                    fullList.addAll(items)
                    val currentItems = (_uiState.value as? Response.Success)?.data.orEmpty()
                    val newList = currentItems + items

                    _uiState.value = Response.Success(newList)
                    if (items.size < pageSize) {
                        isLastPage = true
                    } else {
                        currentPage++
                    }

                    isLoading = false

                }
        }

    }

}

