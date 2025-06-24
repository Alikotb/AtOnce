package com.example.atonce.presentation.cart.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.domain.usecase.GetCartDetailsByIdUseCase
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartDetailsByIdUseCase: GetCartDetailsByIdUseCase,
    private val getPharmacyUseCase: GetPharmacyUseCase,
): ViewModel() {

    val userData = getPharmacyUseCase()

    private val errorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Error: ${throwable.message}")
    }

    fun getCartDetails(){

        viewModelScope.launch(Dispatchers.IO + errorExceptionHandler){
            Log.d("CartTAG", "getCartDetails: $userData")
            getCartDetailsByIdUseCase(pharmacyId = 45 /*userData.id ?: 45*/)
                .collect{ response  ->
                    Log.d("CartTAG", "getCartDetails: $response")
                }

        }
    }

}