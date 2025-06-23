package com.example.atonce.presentation.splash.viewmodel

import androidx.lifecycle.ViewModel
import com.example.atonce.domain.usecase.IsLoggedInUseCase

class SplashViewModel(private val isLoggedInUseCase: IsLoggedInUseCase) : ViewModel() {

    fun isLoggedIn(): Boolean {
        return isLoggedInUseCase()
    }

}