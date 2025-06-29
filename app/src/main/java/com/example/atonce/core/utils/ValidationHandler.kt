package com.example.atonce.core.utils

abstract class ValidationHandler {
    var next: ValidationHandler? = null

    fun setNext(handler: ValidationHandler): ValidationHandler {
        this.next = handler
        return handler
    }

    fun handle() : String? {
        return validate() ?: next?.handle()
    }

    abstract fun validate(): String?
}