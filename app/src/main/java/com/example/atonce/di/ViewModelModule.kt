package com.example.atonce.di

import com.example.atonce.presentation.home.viewmodel.HomeViewModel
import com.example.atonce.presentation.login.viewmodel.LoginViewModel
import com.example.atonce.presentation.search.viewmodel.SearchViewModel
import com.example.atonce.presentation.store.view_model.WarehouseViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { WarehouseViewModel(get(),get()) }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { WarehouseViewModel(get(),get()) }
    viewModel { SearchViewModel(get()) }

}