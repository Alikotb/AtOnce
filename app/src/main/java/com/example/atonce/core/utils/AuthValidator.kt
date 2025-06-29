package com.example.atonce.core.utils

import com.example.atonce.core.enums.ForgotPasswordEnumMessages

object  AuthValidator {
    fun isOtpValid(otp: String): Boolean {
        return otp.length == 5 && otp.all { it.isDigit() }
    }

    fun validatePassword(password: String): String {
        if (password.isEmpty()) {
            return ForgotPasswordEnumMessages.EMPTYFIELDS.getLocalizedMessage()
        }
        if (password.length < 8) {
            return ForgotPasswordEnumMessages.PASSWORDTOOSHORT.getLocalizedMessage()
        }
        if (!password.any { it.isLetter() } || !password.any { it.isDigit() }) {
            return ForgotPasswordEnumMessages.PASSWORDMISSINGCHARDIGIT.getLocalizedMessage()
        }
        return ForgotPasswordEnumMessages.PASSWORDVALID.getLocalizedMessage()
    }

    fun doPasswardMatch(pass: String, confirmPass: String) : String {
        if (pass == confirmPass) {
            return ForgotPasswordEnumMessages.PASSWORDSMATCH.getLocalizedMessage()
        }
        return ForgotPasswordEnumMessages.CONFIRMATIONERROR.getLocalizedMessage()

    }
}