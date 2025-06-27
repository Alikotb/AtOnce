package com.example.atonce.core.utils

class AuthValidator {
    fun isOtpValid(otp: String): Boolean {
        return otp.length == 6 && otp.all { it.isDigit() }
    }
}