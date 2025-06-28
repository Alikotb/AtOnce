package com.example.atonce.core.enums

import java.util.Locale

enum class ErrorMessagesEnum(
    val message: String,
    val arMessage: String
) {

    CONFIRMATIONERROR(
        "Passwords do not match",
        "كلمة المرور غير متطابقة"
    )
    ,EMPTYFIELDS(
        "Please fill in all fields",
        "رجاء قم بملئ جميع البيانات المطلوبة"
    )
    ,
    NETWORKERROR(
        "Check your internet connection",
        "تحقق من الاتصال بالإنترنت"
     ),
    SCANQRCODE(
        "Please scan invitation QR code",
        "رجاء قم بمسح كود الدعوه"),
    REGISTERSUCCESS(
        "Register successfully",
        "تم التسجيل بنجاح"),
    LOGINSUCCESS(
        "Login successful",
        "تم تسجيل الدخول بنجاح"
    ),
    LOGINFAILED(
        "Incorrect email or password",
        "البريد الإلكتروني أو كلمة المرور غير صحيحة"
    ),
    LOGINEXCEPTION(
        "An error occurred during login",
        "حدث خطأ أثناء تسجيل الدخول"
    ),
    DELETESUCCESS(
        "Delete successfully",
        "تم الحذف بنجاح"
    ),
    DELETEEXCEPTION(
        "An error occurred during delete",
        "حدث خطأ أثناء الحذف"
    )

    ;


    fun getLocalizedMessage() : String{
        if (Locale.getDefault().language == "ar"){
            return arMessage
        }
        return message
    }
}