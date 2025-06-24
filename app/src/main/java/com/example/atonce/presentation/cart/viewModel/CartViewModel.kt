package com.example.atonce.presentation.cart.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.data.remote.Response
import com.example.atonce.domain.entity.CartWarehouseEntity
import com.example.atonce.domain.usecase.GetCartDetailsByIdUseCase
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartDetailsByIdUseCase: GetCartDetailsByIdUseCase,
    private val getPharmacyUseCase: GetPharmacyUseCase,
): ViewModel() {

    private val _cartItems = MutableStateFlow<Response<List<CartWarehouseEntity>>>(Response.Loading)
    val cartItems = _cartItems.asStateFlow()


    val userData = getPharmacyUseCase()

    private val errorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Error: ${throwable.message}")
    }

    fun getCartDetails(){
        _cartItems.value = Response.Loading
        viewModelScope.launch(Dispatchers.IO + errorExceptionHandler){
            Log.d("CartTAG", "getCartDetails: $userData")
            getCartDetailsByIdUseCase(pharmacyId = 45 /*userData.id ?: 45*/)
                .collect{ response  ->
                    if (response.success){
                        _cartItems.value = Response.Success(response.warehouses ?: emptyList())
                    }else{
                       _cartItems.value = Response.Success(emptyList())
                    }
                }

        }
    }

}