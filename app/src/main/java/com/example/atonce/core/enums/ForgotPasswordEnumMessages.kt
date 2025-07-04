package com.example.atonce.core.enums

import java.util.Locale

enum class ForgotPasswordEnumMessages(
    val arMessage: String,
    val engMessage: String
) {
    EMPTYFIELDS(
        "رجاء قم بملئ جميع البيانات المطلوبة",
        "Please fill in all fields"
    ),
    PASSWORDTOOSHORT(
        "كلمة المرور يجب أن تكون 8 أحرف على الأقل",
        "Password must be at least 8 characters"
    ),
    PASSWORDMISSINGCHARDIGIT(
        "يجب أن تحتوي كلمة المرور على حروف وأرقام",
        "Password must contain both letters and digits"
    ),
    CONFIRMATIONERROR(
        "كلمة المرور غير متطابقة",
        "Passwords do not match"
    ),
    PASSWORDVALID(
        "كلمة المرور صالحة",
        "Password is valid"
    ),
    PASSWORDSMATCH(
        "كلمتا المرور متطابقتان",
        "Passwords match"
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
        "تحقق من الاتصال بالإنترنت",
        "Check your internet connection"
    ),
    SUCCESS(
    "تمت العملية بنجاح",
    "Operation successful"
    );

    fun getLocalizedMessage() : String{
        if (Locale.getDefault().language == "ar"){
            return arMessage
        }
        return engMessage
    }

}