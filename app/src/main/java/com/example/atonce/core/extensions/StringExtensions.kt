package com.example.atonce.core.extensions

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toLocalizedDateTime(): String {
    return try {
        val isoString = if (this.endsWith("Z").not()) "${this}Z" else this

        val instant = Instant.parse(isoString)
        val dateTime = instant.atZone(ZoneId.systemDefault())

        val currentLocale = Locale.getDefault().language
        val pattern = if (currentLocale == "ar") {
            "dd MMM yyyy - hh:mm a"
        } else {
            "MMM dd, yyyy - hh:mm a"
        }

        val formatter = DateTimeFormatter.ofPattern(pattern, Locale(currentLocale))
        dateTime.format(formatter)
    } catch (e: Exception) {
        this // fallback to original string
    }
}