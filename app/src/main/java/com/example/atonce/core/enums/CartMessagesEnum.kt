package com.example.atonce.core.enums

import java.util.Locale

enum class CartMessagesEnum(
    val engMessage: String,
    val arMessage: String
) {
    ADDEDTOCART(
        "Successfully added to cart",
        "تمت الاضافة الى السلة بنجاح"
    ),
    REMOVEDFROMCART(
        "Removed from cart",
        "تمت الازالة من السلة"
    ),
    EMPTYCART(
        "Your cart is empty",
        "سلة المشتريات فارغة"
    ),
    FAILED(
        "Failed to add to cart",
        "فشل في الاضافة الى السلة"
    );

    fun getMessage() : String {
        if (Locale.getDefault().language == "ar") {
            return arMessage
        }
        return engMessage

    }
}