package com.example.atonce.di

import com.example.atonce.data.repository.AuthRepositoryImpl
import com.example.atonce.data.repository.CartRepositoryImpl
import com.example.atonce.data.repository.MedicineRepositoryImpl
import com.example.atonce.data.repository.OrdersRepositoryImpl
import com.example.atonce.data.repository.WarehouseRepositoryImpl
import com.example.atonce.domain.repository.AuthRepository
import com.example.atonce.domain.repository.CartRepository
import com.example.atonce.domain.repository.MedicineRepository
import com.example.atonce.domain.repository.OrdersRepository
import com.example.atonce.domain.repository.WarehouseRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<WarehouseRepository> {
        WarehouseRepositoryImpl(get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }

    single<MedicineRepository> {
        MedicineRepositoryImpl(get())
    }

    single<CartRepository> {
        CartRepositoryImpl(get())
    }

    single<OrdersRepository> {
        OrdersRepositoryImpl(get())
    }
}