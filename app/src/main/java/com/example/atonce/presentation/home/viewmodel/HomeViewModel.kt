package com.example.atonce.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.core.enums.ErrorMessagesEnum
import com.example.atonce.data.remote.Response
import com.example.atonce.domain.usecase.GetAllWarehousesByAreaUseCase
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import com.example.atonce.presentation.home.model.WarehouseUiModel
import com.example.atonce.presentation.home.model.toUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getWarehousesByAreaUseCase: GetAllWarehousesByAreaUseCase,
    private val getPharmacyUseCase: GetPharmacyUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<Response<List<WarehouseUiModel>>>(Response.Loading)
    val uiState = _uiState.asStateFlow()

    private val _isPaginationLoading = MutableStateFlow(false)
    val isPaginationLoading = _isPaginationLoading.asStateFlow()

    private var currentPage = 1
    private var pageSize = 10
    private var isLastPage = false
    private var isLoading = false

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = Response.Error(ErrorMessagesEnum.NETWORKERROR.getLocalizedMessage())
    }

     fun getWarehousesByArea(areaId: Int, search: String = "") {
        if (isLoading || isLastPage) return
        isLoading = true
         _isPaginationLoading.value = true

        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            getWarehousesByAreaUseCase(areaId, currentPage, pageSize, search)
                .catch { e ->
                    _uiState.value = Response.Error("")
                    isLoading = false
                    _isPaginationLoading.value = false
                }
                .collect { list ->
                    val items = list.map { it.toUiModel() }

                    val currentItems = (_uiState.value as? Response.Success)?.data.orEmpty()
                    val newList = currentItems + items

                    _uiState.value = Response.Success(newList)

                    if (items.size < pageSize) isLastPage = true
                    else currentPage++

                    isLoading = false
                    _isPaginationLoading.value = false
            }
        }
    }

    fun getPharmacyId() : Int {
        return getPharmacyUseCase().areaId!!
    }
}