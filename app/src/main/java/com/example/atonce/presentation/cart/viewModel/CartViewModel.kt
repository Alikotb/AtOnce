package com.example.atonce.presentation.cart.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.domain.usecase.GetCartDetailsByIdUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartDetailsByIdUseCase: GetCartDetailsByIdUseCase
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

}