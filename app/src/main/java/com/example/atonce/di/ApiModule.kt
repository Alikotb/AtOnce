package com.example.atonce.di

import com.example.atonce.data.remote.service.AuthApiService
import com.example.atonce.data.remote.service.MedicineApiService
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

}