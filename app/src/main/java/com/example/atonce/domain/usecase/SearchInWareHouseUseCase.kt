package com.example.atonce.domain.usecase

import com.example.atonce.presentation.store.model.WarehouseMedicines
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SearchInWareHouseUseCase {
    operator fun invoke(
        searchText: String,
        list: List<WarehouseMedicines>
    ): Flow<List<WarehouseMedicines>> {
        return flowOf(
            list.filter {
                it.englishMedicineName.contains(
                    searchText,
                    ignoreCase = true
                )|| it.arabicMedicineName.contains(searchText,ignoreCase = true)
            }
        )

    }
}