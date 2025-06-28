package com.example.atonce

import android.app.Application
import com.example.atonce.di.apiModule
import com.example.atonce.di.internetModule
import com.example.atonce.di.localModule
import com.example.atonce.di.networkModule
import com.example.atonce.di.remoteModule
import com.example.atonce.di.repositoryModule
import com.example.atonce.di.useCaseModule
import com.example.atonce.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }
    override fun onCreate() {
        super.onCreate()
        registerKoin()
    }

    private fun registerKoin() {
        val modules = listOf(
            apiModule,
            internetModule,
            networkModule,
            remoteModule,
            localModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
        startKoin {
            androidContext(this@App)
            modules(modules)
        }

    }
}