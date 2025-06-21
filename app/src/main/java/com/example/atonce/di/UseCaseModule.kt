package com.example.atonce.di

import com.example.atonce.domain.usecase.GetAllMedicinesByWarehousesId
import com.example.atonce.domain.usecase.GetAllWarehousesByAreaUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAllWarehousesByAreaUseCase(get()) }
    factory { GetAllMedicinesByWarehousesId(get()) }
}
