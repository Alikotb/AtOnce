package com.example.atonce.di

import com.example.atonce.data.remote.RemoteDataSource
import com.example.atonce.data.remote.RemoteDataSourceImpl
import org.koin.dsl.module

val remoteModule = module {
    single<RemoteDataSource> {
        RemoteDataSourceImpl(get())
    }
}