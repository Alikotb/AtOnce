package com.example.atonce.data.mappers

import com.example.atonce.data.remote.dto.authentication.LoginResponseDto
import com.example.atonce.data.remote.dto.authentication.PharmacyDto
import com.example.atonce.domain.entity.LoginResult
import com.example.atonce.domain.entity.Pharmacy

fun LoginResponseDto.toEntity() : LoginResult {
    return LoginResult(
        token = token,
        message = message,
        success = success,
        pharmacy = Pharmacy(
            token = token,
            id = pharmacy?.id,
            userName = pharmacy?.userName,
            name = pharmacy?.name,
            email = pharmacy?.email,
            address = pharmacy?.address,
            governate = pharmacy?.governate,
            areaId = pharmacy?.areaId,
            phoneNumber = pharmacy?.phoneNumber,
            representativePhone = pharmacy?.representativePhone
        )
    )
}

fun PharmacyDto.toEntity() : Pharmacy {
    return Pharmacy(
        token = token,
        id = id,
        userName = userName,
        name = name,
        email = email,
        address = address,
        governate = governate,
        areaId = areaId,
        phoneNumber = phoneNumber
    )
}