package com.example.atonce.core.enums

import java.util.Locale

enum class ForgotPasswordEnumMessages(
    val arMessage: String,
    val engMessage: String
) {
    EMPTYFIELDS(
        "Please fill in all fields",
        "رجاء قم بملئ جميع البيانات المطلوبة"
    ),
    CONFIRMATIONERROR(
        "Passwords do not match",
        "كلمة المرور غير متطابقة"
    ),
    INVALIDEMAIL(
        "لم يتم العثور على صيدلية بهذا البريد الإلكتروني.",
        "No pharmacy found with this email."
    ),
    INVALIDOTP(
      "كلمة مرور لمرة واحدة غير صالحة" ,
        "Invalid or expired OTP"
    ),
    NETWORKERROR(
        "Check your internet connection",
        "تحقق من الاتصال بالإنترنت"
    );

    fun getLocalizedMessage() : String{
        if (Locale.getDefault().language == "ar"){
            return arMessage
        }
        return engMessage
    }

}