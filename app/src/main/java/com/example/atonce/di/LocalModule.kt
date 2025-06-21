package com.example.atonce.di

import com.example.atonce.data.local.sharedpreference.SharedPreferences
import com.example.atonce.data.local.sharedpreference.SharedPreferencesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single<SharedPreferences> { SharedPreferencesImpl(androidContext()) }

}