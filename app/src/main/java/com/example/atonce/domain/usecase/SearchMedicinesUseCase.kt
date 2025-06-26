package com.example.atonce.domain.usecase

import com.example.atonce.domain.repository.MedicineRepository

class SearchMedicinesUseCase(private val repo: MedicineRepository) {
    suspend operator fun invoke(
        areaId: Int,
        page: Int,
        pageSize: Int,
        type: String,
        search: String) = repo.searchMedicinesByNameAndArea(areaId, page, pageSize, type, search)
}