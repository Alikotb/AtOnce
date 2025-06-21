package com.example.atonce.di

import com.example.atonce.presentation.home.viewmodel.HomeViewModel
import com.example.atonce.presentation.login.viewmodel.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}