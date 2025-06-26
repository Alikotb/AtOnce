package com.example.atonce.domain.repository

import com.example.atonce.domain.entity.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    suspend fun searchMedicinesByNameAndArea(areaId: Int, page: Int, pageSize: Int, type: String, search: String): Flow<List<Medicine>>
}