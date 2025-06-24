package com.example.atonce.di

import com.example.atonce.data.internet.ConnectivityObserverImp
import com.example.atonce.domain.internet.ConnectivityObserver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val internetModule = module {
    single<ConnectivityObserver> { ConnectivityObserverImp(androidContext()) }
}