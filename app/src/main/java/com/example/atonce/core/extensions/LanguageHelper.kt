@file:Suppress("DEPRECATION")

package com.example.atonce.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import java.util.Locale

fun Context.applyLanguage(languageCode: String): Context {
   val locale = Locale(languageCode)
   Locale.setDefault(locale)

   val config = Configuration(resources.configuration)
   config.setLocale(locale)
   config.setLayoutDirection(locale)

   return createConfigurationContext(config)
}

fun Context.restartActivity() {
   val intent = (this as? Activity)?.intent
   intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
   this.startActivity(intent)
   (this as? Activity)?.finish()
}

fun String.convertNumbersToArabic(): String {
   return if (Locale.getDefault().language == "ar") {
      this.map { char ->
         if (char in '0'..'9') {
            ('\u0660' + (char - '0')).toChar()
         } else {
            char
         }
      }.joinToString("")
   } else {
      this
   }
}
