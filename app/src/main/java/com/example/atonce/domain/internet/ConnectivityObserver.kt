package com.example.atonce.domain.internet

import androidx.lifecycle.LiveData

interface ConnectivityObserver {
    val isOnline: LiveData<Boolean>
}