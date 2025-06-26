package com.example.atonce.core.enums

import java.util.Locale

enum class FilterOptions(val arabic: String, val english: String, val value: String) {
    DRUG("أدوية", "Drug", "1"),
    COSMETICS("مستحضرات تجميل", "Cosmetics", "0"),
    ALL("الجميع", "All", "");

    fun getLocalizedName(): String {
        return if (Locale.getDefault().language == "ar") arabic else english
    }

    companion object {
        fun geValue(value: String): String {
            val lang = Locale.getDefault().language
            return when (lang) {
                "ar" -> entries.find { it.arabic == value }?.value ?: ALL.value
                else -> entries.find { it.english == value }?.value ?: ALL.value
            }
        }

        fun getName(value: String): String {
            val lang = Locale.getDefault().language
            return when (lang) {
                "ar" -> entries.find { it.value == value }?.arabic ?: ALL.arabic
                else -> entries.find { it.value == value }?.english ?: ALL.english
            }
        }
        fun getAllLocalizedNames(): List<String> {
            return entries.map { it.getLocalizedName() }
        }
    }
}
