package com.example.atonce.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.domain.entity.Response
import com.example.atonce.domain.usecase.GetAllWarehousesByAreaUseCase
import com.example.atonce.presentation.home.model.WarehouseUiModel
import com.example.atonce.presentation.home.model.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val getWarehousesByAreaUseCase: GetAllWarehousesByAreaUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<Response<List<WarehouseUiModel>>>(Response.Loading)
    val uiState = _uiState.asStateFlow()

    private var currentPage = 1
    private var pageSize = 10
    private var isLastPage = false
    private var isLoading = false

    suspend fun getWarehousesByArea(areaId: Int, search: String = "") {
        if (isLoading || isLastPage) return
        isLoading = true

        viewModelScope.launch(Dispatchers.IO) {
            getWarehousesByAreaUseCase(areaId, currentPage, pageSize, search)
                .catch {
                    _uiState.value = Response.Error("")
                    isLoading = false
                }
                .collect { list ->
                    val items = list.map { it.toUiModel() }

                    val currentItems = (_uiState.value as? Response.Success)?.data.orEmpty()
                    val newList = currentItems + items

                    _uiState.value = Response.Success(newList)

                    if (items.size < pageSize) isLastPage = true
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
}