package com.example.atonce.presentation.cart.viewModel

import androidx.lifecycle.ViewModel
import com.example.atonce.domain.usecase.GetCartDetailsById

class CartViewModel(
    private val getCartDetailsById: GetCartDetailsById
): ViewModel() {


}