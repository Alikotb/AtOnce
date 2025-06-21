package com.example.atonce.presentation.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.data.remote.Response
import com.example.atonce.domain.entity.LoginResult
import com.example.atonce.domain.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<Response<LoginResult>>(Response.Loading)
    val uiState = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(email, password)
                .catch { e ->
                    _uiState.value = Response.Error("")
                    Log.d("TAG", "getWarehousesByArea: ${e.message}")
                }
                .collect { result ->

                }
        }

    }
}