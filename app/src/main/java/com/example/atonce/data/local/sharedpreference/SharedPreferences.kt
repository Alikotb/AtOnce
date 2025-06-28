package com.example.atonce.data.local.sharedpreference

interface SharedPreferences {
    fun <T> saveData(key: String, value: T?)
    fun <T> fetchData(key: String, defaultValue: T): T
}