package com.example.atonce.presentation.cart.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.core.enums.ErrorMessagesEnum
import com.example.atonce.data.remote.Response
import com.example.atonce.data.remote.dto.PlaceOrderResponse
import com.example.atonce.data.remote.dto.cart.UpdateCartRequest
import com.example.atonce.domain.entity.CartWarehouseEntity
import com.example.atonce.domain.usecase.DeleteFromCartUseCase
import com.example.atonce.domain.usecase.GetCartDetailsByIdUseCase
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import com.example.atonce.domain.usecase.PlaceOrderUseCase
import com.example.atonce.domain.usecase.UpdateCartUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(
    private val getCartDetailsByIdUseCase: GetCartDetailsByIdUseCase,
    private val getPharmacyUseCase: GetPharmacyUseCase,
    private val deleteFromCartUseCase: DeleteFromCartUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val placeOrderUseCase: PlaceOrderUseCase
    ) : ViewModel()  {

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

    fun getCartDetails() {
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

    fun deleteFromCart(
        wareHouseId: Int,
        medicineId: Int
    ) {
        viewModelScope.launch(Dispatchers.IO + errorExceptionHandler) {


            deleteFromCartUseCase(userData.id ?: 0, wareHouseId, medicineId)
                .collect { response ->
                    if (response.success) {
                        _message.emit(ErrorMessagesEnum.DELETESUCCESS.getLocalizedMessage())
                        getCartDetails()
                    }
                    else {
                        _message.emit(ErrorMessagesEnum.DELETEEXCEPTION.getLocalizedMessage())
                    }
                }
        }

    }



    private suspend fun updateCart(request: UpdateCartRequest): Boolean {
        return try {
            if (request.newQuantity < 1) {
                deleteFromCart(wareHouseId = request.warehouseId, medicineId = request.medicineId)
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

    private suspend fun placeOrder(wareHouseId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                var result = false
                placeOrderUseCase(wareHouseId, getPharmacyUseCase().id ?: 0).collect { res: PlaceOrderResponse ->
                    _message.emit("order done successfully")
                    result = res.success
                }
                result
            } catch (e: Exception) {
                _message.emit(e.message ?: "can't update cart")
                false
            }
        }
    }

    fun placeCartAndRefresh(wareHouseId:Int) {
        viewModelScope.launch {
            _isUpdated.emit(false)
            val success = placeOrder(wareHouseId)
            if (success) getCartDetails()
            _isUpdated.emit(true)

        }
    }

}