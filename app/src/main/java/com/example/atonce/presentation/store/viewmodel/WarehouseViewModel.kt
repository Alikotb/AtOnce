package com.example.atonce.presentation.store.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.core.enums.CartMessagesEnum
import com.example.atonce.data.mappers.toEntity
import com.example.atonce.domain.Response
import com.example.atonce.data.remote.dto.Warehouse.WarehouseMedicinesDto
import com.example.atonce.domain.usecase.AddToCartUseCase
import com.example.atonce.domain.usecase.GetAllMedicinesByWarehousesId
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import com.example.atonce.presentation.search.model.AddToCartUiModel
import com.example.atonce.presentation.search.model.toEntity
import com.example.atonce.presentation.store.model.WarehouseMedicines
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@FlowPreview
class WarehouseViewModel(
    private val getAllMedicinesByWarehousesId: GetAllMedicinesByWarehousesId,
    private val addToCartUseCase: AddToCartUseCase,
    private val getPharmacyUseCase: GetPharmacyUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<Response<List<WarehouseMedicines>>>(Response.Loading)
    val uiState = _uiState.asStateFlow()
    private val fullList = mutableListOf<WarehouseMedicines>()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    private val _loadingItemId = MutableStateFlow<Int?>(null)
    val loadingItemId = _loadingItemId.asStateFlow()

    private val _filterType = MutableStateFlow("")
    val filterType = _filterType.asStateFlow()

    private var currentPage = 1
    private var pageSize = 10
    private var isLastPage = false
    private var isLoading = false

    val handler = CoroutineExceptionHandler { _, exception ->
    _uiState.value=Response.Error(exception.message?:"")
    }



     fun initFunSearch(warehouseId: Int) {
        viewModelScope.launch(handler) {
            combine(_searchQuery.debounce(500), _filterType) { search, filter ->
                search to filter
            }.distinctUntilChanged()
                .collectLatest { (search, filter) ->
                    resetPagination()
                    getAllMedicinesByStoreId(warehouseId, search, filter)
                }
        }
    }

    fun onFilterChanged(type: String) {
        _filterType.value = type
    }
    fun onSearchChanged(query: String) {
        _searchQuery.value = query
    }

    fun getAllMedicinesByStoreId(warehouseId: Int, search: String, type: String) {
        if (isLoading || isLastPage) return
        isLoading = true

        viewModelScope.launch(Dispatchers.IO + handler) {
            getAllMedicinesByWarehousesId(
                warehouseId = warehouseId,
                pageNum = currentPage,
                pageSize = pageSize,
                search = search,
                type = type
            ).catch {
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

    fun addToCart(warehouseId: Int, medicineId: Int) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _loadingItemId.value = medicineId
            val result  = addToCartUseCase(AddToCartUiModel(warehouseId = warehouseId, pharmacyId = getPharmacyUseCase().id!!, medicineId = medicineId, quantity = 1).toEntity())
            _loadingItemId.value = null
            if (result.isSuccessful) {
                _message.emit(CartMessagesEnum.ADDEDTOCART.getMessage())
            } else {
                _message.emit(CartMessagesEnum.FAILED.getMessage())
            }
        }
    }


    fun resetPagination() {
        currentPage = 1
        isLastPage = false
        _uiState.value = Response.Loading
    }

}

