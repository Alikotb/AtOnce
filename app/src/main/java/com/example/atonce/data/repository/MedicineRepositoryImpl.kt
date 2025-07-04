package com.example.atonce.data.repository

import com.example.atonce.data.mappers.toEntity
import com.example.atonce.data.remote.service.MedicineApiService
import com.example.atonce.domain.entity.Medicine
import com.example.atonce.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MedicineRepositoryImpl(
    private val service: MedicineApiService
) : MedicineRepository {

    override suspend fun searchMedicinesByNameAndArea(
        areaId: Int,
        page: Int,
        pageSize: Int,
        type: String,
        search: String): Flow<List<Medicine>> {
        return flowOf(service.searchMedicinesByNameAndArea(areaId,
            page,
            pageSize,
            type,
            search).items.map {
                it.toEntity()
            })
    }
}
