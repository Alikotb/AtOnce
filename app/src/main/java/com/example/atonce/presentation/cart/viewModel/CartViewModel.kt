package com.example.atonce.presentation.cart.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.data.remote.dto.cart.UpdateCartRequest
import com.example.atonce.domain.usecase.GetCartDetailsByIdUseCase
import com.example.atonce.domain.usecase.UpdateCartUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartDetailsByIdUseCase: GetCartDetailsByIdUseCase,
    private val updateCartUseCase: UpdateCartUseCase
): ViewModel() {

    private val errorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Error: ${throwable.message}")
    }

    fun getCartDetails(cartId: Int){

        viewModelScope.launch(Dispatchers.IO + errorExceptionHandler){

            getCartDetailsByIdUseCase(cartId)
                .collect{ response  ->
                    Log.d("TAG", "getCartDetails: $response")
                }

        }
    }

    fun updateCart(request: UpdateCartRequest){




        viewModelScope.launch (Dispatchers.IO){
            updateCartUseCase(request).catch{

            }.collect {

            }
        }
    }



}