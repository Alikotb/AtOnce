package com.example.atonce.presentation.cart.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.core.enums.ErrorMessagesEnum
import com.example.atonce.data.remote.Response
import com.example.atonce.domain.entity.CartWarehouseEntity
import com.example.atonce.domain.usecase.DeleteFromCartUseCase
import com.example.atonce.domain.usecase.GetCartDetailsByIdUseCase
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartDetailsByIdUseCase: GetCartDetailsByIdUseCase,
    private val getPharmacyUseCase: GetPharmacyUseCase,
    private val deleteFromCartUseCase: DeleteFromCartUseCase
) : ViewModel() {

    private val _cartItems = MutableStateFlow<Response<List<CartWarehouseEntity>>>(Response.Loading)
    val cartItems = _cartItems.asStateFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()


    val userData = getPharmacyUseCase()

    private val errorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Error: ${throwable.message}")
    }

    fun getCartDetails() {
        _cartItems.value = Response.Loading
        viewModelScope.launch(Dispatchers.IO + errorExceptionHandler) {
            Log.d("CartTAG", "getCartDetails: $userData")
            getCartDetailsByIdUseCase(pharmacyId = userData.id ?: 0)
                .collect { response ->
                    if (response.success) {
                        _cartItems.value = Response.Success(response.warehouses ?: emptyList())
                    } else {
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
            Log.d("CartTAG", "deleteFromCart: $userData")
            Log.d("CartTAG", "deleteFromCart: $wareHouseId")
            Log.d("CartTAG", "deleteFromCart: $medicineId")

            deleteFromCartUseCase(userData.id ?: 0, wareHouseId, medicineId)
                .collect { response ->
                    Log.d("CartTAG", "deleteFromCart: $response")
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

}