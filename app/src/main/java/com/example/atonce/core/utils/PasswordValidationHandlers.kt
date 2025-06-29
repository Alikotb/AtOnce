package com.example.atonce.core.utils

import com.example.atonce.core.enums.ForgotPasswordEnumMessages

class EmptyPasswordHandler(private val password: String) : ValidationHandler() {
    override fun validate() = if (password.isEmpty())
        ForgotPasswordEnumMessages.EMPTYFIELDS.getLocalizedMessage()
    else null
}

class PasswordLengthHandler(private val password: String) : ValidationHandler() {
    override fun validate() = if (password.length < 8)
        ForgotPasswordEnumMessages.PASSWORDTOOSHORT.getLocalizedMessage()
    else null
}

class PasswordCharDigitHandler(private val password: String) : ValidationHandler() {
    override fun validate() = if (!password.any { it.isLetter() } || !password.any { it.isDigit() })
        ForgotPasswordEnumMessages.PASSWORDMISSINGCHARDIGIT.getLocalizedMessage()
    else null
}

class ConfirmPasswordHandler(private val password: String, private val confirmPassword: String) : ValidationHandler() {
    override fun validate() = if (password != confirmPassword)
        ForgotPasswordEnumMessages.CONFIRMATIONERROR.getLocalizedMessage()
    else null
}
