package com.example.atonce.presentation.cart.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.data.remote.dto.cart.UpdateCartRequest
import com.example.atonce.data.remote.Response
import com.example.atonce.data.remote.dto.cart.UpdateCartResponse
import com.example.atonce.domain.entity.CartWarehouseEntity
import com.example.atonce.domain.usecase.GetCartDetailsByIdUseCase
import com.example.atonce.domain.usecase.UpdateCartUseCase
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartDetailsByIdUseCase: GetCartDetailsByIdUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val getPharmacyUseCase: GetPharmacyUseCase
): ViewModel() {

    private val _cartItems = MutableStateFlow<Response<List<CartWarehouseEntity>>>(Response.Loading)
    val cartItems = _cartItems.asStateFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    private val _isUpdated = MutableStateFlow<Boolean>(true)
    val isUpdated = _isUpdated.asStateFlow()

    val userData = getPharmacyUseCase()

    private val errorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Error: ${throwable.message}")
    }

    fun getCartDetails(){
        _cartItems.value = Response.Loading
        viewModelScope.launch(Dispatchers.IO + errorExceptionHandler){
            Log.d("CartTAG", "getCartDetails: $userData")
            getCartDetailsByIdUseCase(pharmacyId = userData.id ?: 0)
                .collect{ response  ->
                    if (response.success){
                        _cartItems.value = Response.Success(
                            (response.warehouses ?: emptyList()).map { warehouse ->
                                warehouse.copy(
                                    items = warehouse.items.map { it.copy() }
                                )
                            }
                        )                    }else{
                       _cartItems.value = Response.Success(emptyList())
                    }
                }

        }
    }



    suspend fun updateCart(request: UpdateCartRequest): Boolean {
        return try {
            if (request.newQuantity < 1) {
                _message.emit("deleted successfully")
                return true
            }

            updateCartUseCase(getPharmacyUseCase().id ?: 0, request).collect { response ->
                _message.emit(response.message)
            }

            true
        } catch (e: Exception) {
            _message.emit(e.message ?: "can't update cart")
            false
        }
    }

    fun updateCartAndRefresh(request: UpdateCartRequest) {
        viewModelScope.launch {
            _isUpdated.emit(false)
            val success = updateCart(request)
            if (success) getCartDetails()
            _isUpdated.emit(true)

        }
    }


}