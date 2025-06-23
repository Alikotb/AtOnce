package com.example.atonce.domain.repository

import com.example.atonce.domain.entity.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    suspend fun searchMedicinesByNameAndArea(areaId: Int, page: Int, pageSize: Int, search: String): Flow<List<Medicine>>
}