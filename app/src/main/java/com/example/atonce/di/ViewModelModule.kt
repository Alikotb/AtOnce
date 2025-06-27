package com.example.atonce.di

import com.example.atonce.presentation.cart.viewModel.CartViewModel
import com.example.atonce.presentation.forgotpassword.viewmodel.ForgotPasswordViewModel
import com.example.atonce.presentation.home.viewmodel.HomeViewModel
import com.example.atonce.presentation.login.viewmodel.LoginViewModel
import com.example.atonce.presentation.signup.SignUpViewModel
import com.example.atonce.presentation.profile.viewmodel.ProfileViewModel
import com.example.atonce.presentation.search.viewmodel.SearchViewModel
import com.example.atonce.presentation.splash.viewmodel.SplashViewModel
import com.example.atonce.presentation.store.viewmodel.WarehouseViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { WarehouseViewModel(get(), get(), get()) }
    viewModel { SignUpViewModel(get(),get() , get()) }
    viewModel { SearchViewModel(get() , get(), get(), get(), get()) }
    viewModel { ProfileViewModel(get(),get(),get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { CartViewModel(get(),get(),get(),get(),get()) }
    viewModel { ForgotPasswordViewModel(get(), get()) }

}