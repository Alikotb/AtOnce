package com.example.atonce.presentation.search.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.core.enums.CartMessagesEnum
import com.example.atonce.domain.Response
import com.example.atonce.domain.entity.Medicine
import com.example.atonce.domain.entity.SupplierEntity
import com.example.atonce.domain.usecase.AddToCartUseCase
import com.example.atonce.domain.usecase.GetAllSuppliersByAreaIdAndMedicine
import com.example.atonce.domain.usecase.GetLanguageUseCase
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import com.example.atonce.domain.usecase.SearchMedicinesUseCase
import com.example.atonce.presentation.search.model.AddToCartUiModel
import com.example.atonce.presentation.search.model.toEntity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@FlowPreview
class SearchViewModel(
    private val searchMedicinesUseCase: SearchMedicinesUseCase,
    private val getAllSuppliersByAreaIdAndMedicine: GetAllSuppliersByAreaIdAndMedicine,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val getPharmacyUseCase: GetPharmacyUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<Response<List<Medicine>>>(Response.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiStateSuppliers = MutableStateFlow<Response<List<SupplierEntity>>>(Response.Loading)
    val uiStateSuppliers = _uiStateSuppliers.asStateFlow()

    private val _currentLanguage = mutableStateOf("")
    val currentLanguage = _currentLanguage

    private val _areaId = mutableStateOf(getPharmacyUseCase().areaId!!)
    val areaId = _areaId

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    private val _loadingItemId = MutableStateFlow<Pair<Int?,Int?>?>(null)
    val loadingItemId = _loadingItemId.asStateFlow()

    private val _isPaginationLoading = MutableStateFlow(false)
    val isPaginationLoading = _isPaginationLoading.asStateFlow()

    private var currentPage = 1
    private var pageSize = 15
    private var isLastPage = false
    private var isLoading = false

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _selectedType = MutableStateFlow("")
    val selectedType = _selectedType.asStateFlow()

    val handler = CoroutineExceptionHandler { _, exception ->

    }

    init {
        getLanguage()
        viewModelScope.launch(handler) {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    resetPagination()
                    getMedicinesByArea(getPharmacyUseCase().areaId!!,_selectedType.value, query)
                }
        }
    }

    fun onSearchChanged(query: String) {
        _searchQuery.value = query
    }

    fun setSelectedType(type: String) {
        _selectedType.value = type
        resetPagination()
        getMedicinesByArea(getPharmacyUseCase().areaId!!, type, _searchQuery.value)
    }

    fun getMedicinesByArea(areaId: Int, type: String = "", search: String) {
        if (isLoading || isLastPage) return
        isLoading = true
        _isPaginationLoading.value = true

        viewModelScope.launch(Dispatchers.IO + handler) {
            searchMedicinesUseCase(areaId, currentPage, pageSize, type, search)
                .catch { e ->
                    _uiState.value = Response.Error("")
                    isLoading = false
                    _isPaginationLoading.value = false
                }
                .collect { list ->
                    val currentItems = (_uiState.value as? Response.Success)?.data.orEmpty()
                    val newList = currentItems + list

                    _uiState.value = Response.Success(newList)

                    if (list.size < pageSize) isLastPage = true
                    else currentPage++

                    isLoading = false
                    _isPaginationLoading.value = false
                }
        }
    }

    fun resetPagination() {
        currentPage = 1
        isLastPage = false
        _uiState.value = Response.Loading
    }

    fun getAllSuppliers(medicineId: Int, areaId: Int){
        viewModelScope.launch (Dispatchers.IO + handler){
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

    private fun getLanguage() {
        _currentLanguage.value = getLanguageUseCase()
    }

    fun addToCart(warehouseId: Int, medicineId: Int) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _loadingItemId.value = Pair(warehouseId,medicineId)
            val result  = addToCartUseCase(AddToCartUiModel(warehouseId = warehouseId, pharmacyId = getPharmacyUseCase().id!!, medicineId = medicineId, quantity = 1).toEntity())
            _loadingItemId.value = null
            if (result.isSuccessful) {
                _message.emit(CartMessagesEnum.ADDEDTOCART.getMessage())
            } else {
                _message.emit(CartMessagesEnum.FAILED.getMessage())
            }
        }
    }
}