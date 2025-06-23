package com.example.atonce.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.atonce.data.mappers.toEntity
import com.example.atonce.domain.usecase.FreePharmacyUseCase
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import com.example.atonce.domain.usecase.SaveLanguageUseCase

class ProfileViewModel(
    private val getPharmacyUseCase: GetPharmacyUseCase,
    private val freePharmacyUseCase: FreePharmacyUseCase,
    private val saveLanguageUseCase: SaveLanguageUseCase
) : ViewModel() {
    val userData = getPharmacyUseCase().toEntity()
    fun logOut() {
        freePharmacyUseCase()
    }
    fun changeLanguage(code: String){
        saveLanguageUseCase(code)

    }
}