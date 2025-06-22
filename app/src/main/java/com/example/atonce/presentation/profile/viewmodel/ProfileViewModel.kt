package com.example.atonce.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.atonce.data.mappers.toEntity
import com.example.atonce.domain.usecase.FreePharmacyUseCase
import com.example.atonce.domain.usecase.GetPharmacyUseCase

class ProfileViewModel(
    private val getPharmacyUseCase: GetPharmacyUseCase,
    private val freePharmacyUseCase: FreePharmacyUseCase
) : ViewModel() {
    val userData = getPharmacyUseCase().toEntity()
    fun logOut() {
        freePharmacyUseCase()
    }
}