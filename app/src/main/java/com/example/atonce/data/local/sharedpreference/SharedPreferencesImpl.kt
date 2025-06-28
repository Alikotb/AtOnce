package com.example.atonce.data.local.sharedpreference

import android.content.Context
import android.content.SharedPreferences


class SharedPreferencesImpl(context: Context) :
    com.example.atonce.data.local.sharedpreference.SharedPreferences {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("", Context.MODE_PRIVATE)


    override fun <T> saveData(key: String, value: T?) {
        with(sharedPreferences.edit()) {
            when (value) {
                null -> remove(key)
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                is Double -> putFloat(key, value.toFloat())
                else -> throw IllegalArgumentException("Unsupported type")
            }
            apply()
        }
    }


    override fun <T> fetchData(key: String, defaultValue: T): T {
        @Suppress("UNCHECKED_CAST")
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Long -> sharedPreferences.getLong(key, defaultValue) as T
            is Double -> sharedPreferences.getFloat(key, defaultValue.toFloat()).toDouble() as T
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }
}
