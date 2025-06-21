package com.example.atonce.di

import com.example.atonce.domain.usecase.GetAllWarehousesByAreaUseCase
import com.example.atonce.domain.usecase.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAllWarehousesByAreaUseCase(get()) }

    factory { LoginUseCase(get()) }
}