package com.example.atonce.data.mappers

import com.example.atonce.domain.entity.Pharmacy
import com.example.atonce.domain.entity.PharmacyEntity

fun Pharmacy.toEntity(): PharmacyEntity {
    return PharmacyEntity(
        token = token?:"",
        id = id?:-1,
        userName = userName?:"",
        pharmacyName = name?:"",
        email = email?:"",
        address = address?:"",
        governorate = governate?:"",
        areaId = areaId?:-1,
        phoneNumber = phoneNumber?:"",
        representativePhone = representativePhone?:""
    )
}