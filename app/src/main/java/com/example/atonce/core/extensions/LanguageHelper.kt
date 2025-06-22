package com.example.atonce.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.util.Locale

 fun Context.applyLanguage(languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val config = resources.configuration
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)
}

fun Context.restartActivity() {
   val intent = (this as? Activity)?.intent
   intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
   this.startActivity(intent)
   (this as? Activity)?.finish()
}
