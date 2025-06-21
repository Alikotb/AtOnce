package com.example.atonce.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://www.pharmaatoncepredeploy.somee.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}