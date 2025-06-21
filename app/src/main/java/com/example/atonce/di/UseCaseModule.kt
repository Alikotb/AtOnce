package com.example.atonce.di

import com.example.atonce.domain.usecase.GetAllMedicinesByWarehousesId
import com.example.atonce.domain.usecase.GetAllWarehousesByAreaUseCase
import com.example.atonce.domain.usecase.LoginUseCase
import com.example.atonce.domain.usecase.SearchInWareHouseUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAllWarehousesByAreaUseCase(get()) }

    factory { LoginUseCase(get()) }
    factory { GetAllMedicinesByWarehousesId(get()) }
    factory { SearchInWareHouseUseCase() }

}
