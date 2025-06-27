package com.example.atonce.core.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CancelPresentation
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Locale

enum class OrderState(val arabic: String, val english: String, val value: Int,val icon: ImageVector) {
    ORDERED("تم الطلب", "Ordered",0,Icons.Filled.Loop),
    PREPARING("قيد التحضير", "Preparing", 4,Icons.Filled.Upcoming),
    DELIVERING("جارٍ التوصيل", "Delivering", 5,Icons.Filled.DeliveryDining),
    DELIVERED("تم التوصيل", "Delivered", 1,Icons.Filled.ShoppingBasket),
    CANCELED("تم الإلغاء", "Canceled", 2,Icons.Filled.Cancel),
    RETURNED("تم الإرجاع", "Returned",3,Icons.Filled.CancelPresentation);

    companion object {
        fun getName(value: Int): String {
            val lang = Locale.getDefault().language
            return when (lang) {
                "ar" -> entries.find { it.value == value }?.arabic ?: ORDERED.arabic
                else -> entries.find { it.value == value }?.english ?: ORDERED.english
            }
        }
    }
}
