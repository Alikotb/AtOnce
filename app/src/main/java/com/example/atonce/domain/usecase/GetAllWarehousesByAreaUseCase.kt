package com.example.atonce.domain.usecase

import com.example.atonce.domain.repository.WarehouseRepository

class GetAllWarehousesByAreaUseCase(private val repo: WarehouseRepository) {
    suspend operator fun invoke(areaId: Int, page: Int, pageSize: Int, search: String) =
        repo.getAllWarehousesByArea(areaId, page, pageSize, search)
}