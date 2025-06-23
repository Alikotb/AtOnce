package com.example.atonce.presentation.search.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.data.remote.Response
import com.example.atonce.domain.entity.Medicine
import com.example.atonce.domain.entity.SupplierEntity
import com.example.atonce.domain.usecase.GetAllSuppliersByAreaIdAndMedicine
import com.example.atonce.domain.usecase.SearchMedicinesUseCase
import com.example.atonce.presentation.home.model.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchViewModel(private val searchMedicinesUseCase: SearchMedicinesUseCase,
    private val getAllSuppliersByAreaIdAndMedicine: GetAllSuppliersByAreaIdAndMedicine
) : ViewModel() {
    private val _uiState = MutableStateFlow<Response<List<Medicine>>>(Response.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiStateSuppliers = MutableStateFlow<Response<List<SupplierEntity>>>(Response.Loading)
    val uiStateSuppliers = _uiStateSuppliers.asStateFlow()

    private var currentPage = 1
    private var pageSize = 15
    private var isLastPage = false
    private var isLoading = false

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    resetPagination()
                    getMedicinesByArea(3, query)
                }
        }
    }

    fun onSearchChanged(query: String) {
        _searchQuery.value = query
    }

    fun getMedicinesByArea(areaId: Int, search: String) {
        if (isLoading || isLastPage) return
        isLoading = true

        viewModelScope.launch(Dispatchers.IO) {
            searchMedicinesUseCase(areaId, currentPage, pageSize, search)
                .catch { e ->
                    _uiState.value = Response.Error("")
                    isLoading = false
                }
                .collect { list ->
                    val currentItems = (_uiState.value as? Response.Success)?.data.orEmpty()
                    val newList = currentItems + list

                    _uiState.value = Response.Success(newList)
                    Log.d("TAG", "getMedicinesByArea: $newList")

                    if (list.size < pageSize) isLastPage = true
                    else currentPage++

                    isLoading = false
                }
        }
    }

    fun resetPagination() {
        currentPage = 1
        isLastPage = false
        _uiState.value = Response.Loading
    }

    fun getAllSuppliers(medicineId: Int, areaId: Int){
        viewModelScope.launch (Dispatchers.IO){
            getAllSuppliersByAreaIdAndMedicine(areaId, medicineId)
                .catch { e ->
                    _uiStateSuppliers.value = Response.Error(e.message.toString())
                }
                .collectLatest { list ->
                    _uiStateSuppliers.value = Response.Success(list)
                }
        }
    }

    fun freeUiState(){
        _uiStateSuppliers.value = Response.Loading
    }
}