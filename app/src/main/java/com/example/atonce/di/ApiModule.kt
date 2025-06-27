package com.example.atonce.di

import com.example.atonce.data.remote.service.AuthApiService
import com.example.atonce.data.remote.service.CartApiService
import com.example.atonce.data.remote.service.MedicineApiService
import com.example.atonce.data.remote.service.OrdersApiService
import com.example.atonce.data.remote.service.WarehouseApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<WarehouseApiService> {
        get<Retrofit>().create(WarehouseApiService::class.java)
    }

    single<AuthApiService> {
        get<Retrofit>().create(AuthApiService::class.java)
    }

    single<MedicineApiService> {
        get<Retrofit>().create(MedicineApiService::class.java)
    }

    single<CartApiService> {
        get<Retrofit>().create(CartApiService::class.java)
    }

    single<OrdersApiService> {
        get<Retrofit>().create(OrdersApiService::class.java)

    }

}