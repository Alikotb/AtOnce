package com.example.atonce.di

import com.example.atonce.data.repository.AuthRepositoryImpl
import com.example.atonce.data.repository.WarehouseRepositoryImpl
import com.example.atonce.domain.repository.AuthRepository
import com.example.atonce.domain.repository.WarehouseRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<WarehouseRepository> {
        WarehouseRepositoryImpl(get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }

}