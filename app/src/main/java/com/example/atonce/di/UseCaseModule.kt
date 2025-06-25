package com.example.atonce.di

import com.example.atonce.domain.usecase.AddToCartUseCase
import com.example.atonce.domain.usecase.DeleteFromCartUseCase
import com.example.atonce.domain.usecase.FreePharmacyUseCase
import com.example.atonce.domain.usecase.GetAllMedicinesByWarehousesId
import com.example.atonce.domain.usecase.GetAllSuppliersByAreaIdAndMedicine
import com.example.atonce.domain.usecase.GetAllWarehousesByAreaUseCase
import com.example.atonce.domain.usecase.GetAreasUseCase
import com.example.atonce.domain.usecase.GetCartDetailsByIdUseCase
import com.example.atonce.domain.usecase.GetGovernoratesUseCase
import com.example.atonce.domain.usecase.GetLanguageUseCase
import com.example.atonce.domain.usecase.GetPharmacyUseCase
import com.example.atonce.domain.usecase.IsLoggedInUseCase
import com.example.atonce.domain.usecase.LoginUseCase
import com.example.atonce.domain.usecase.RegisterUseCase
import com.example.atonce.domain.usecase.SaveLanguageUseCase
import com.example.atonce.domain.usecase.SavePharmacyUseCase
import com.example.atonce.domain.usecase.SearchMedicinesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAllWarehousesByAreaUseCase(get()) }

    factory { LoginUseCase(get()) }
    factory { IsLoggedInUseCase(get()) }

    factory { GetAllMedicinesByWarehousesId(get()) }
    factory { FreePharmacyUseCase(get()) }

    factory { SavePharmacyUseCase(get()) }
    factory { GetPharmacyUseCase(get()) }
    factory { GetGovernoratesUseCase(get()) }
    factory { GetAreasUseCase(get()) }

    factory { RegisterUseCase(get()) }

    factory { SearchMedicinesUseCase(get()) }
    factory { SaveLanguageUseCase(get()) }

    factory { GetLanguageUseCase(get()) }

    factory { GetAllSuppliersByAreaIdAndMedicine(get()) }

    factory { GetCartDetailsByIdUseCase(get()) }
    factory { AddToCartUseCase(get()) }

    factory { DeleteFromCartUseCase(get()) }
}
