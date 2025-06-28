package com.example.atonce.data.remote.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.koin.test.KoinTest

import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class WarehouseApiServiceTest : KoinTest {
    val warehouseApiService: WarehouseApiService by inject()

    @Test
    fun getAllWarehousesByArea_AreaId3_Return10Warehouses() {
        runBlocking {
            //given
            val areaId = 3
            val pageSize = 10
            val page = 1
            //when
            val result = warehouseApiService.getAllWarehousesByArea(
                areaId = areaId,
                pageSize = pageSize,
                page = page,
                search = ""
            )

            //then
            assertNotNull(result)
            assertTrue(result.items.size == 10)
            assertTrue(result.items[1].id == 2)
            assertTrue(result.items.first().id == 5)
            assertTrue(result.items.first().address == "25 Tahrir Square")
            assertTrue(result.items[1].governate == "Cairo")
        }

    }

    @Test
    fun getAllMedicinesByWarehousesId_WarehouseId2_Return20Medicine() {
        runBlocking {
            //given
            val warehouseId = 2
            val pageSize = 100
            val page = 1
            //when
            val result = warehouseApiService.getAllMedicinesByWarehousesId(
                warehouseId = warehouseId,
                pageSize = pageSize,
                pageNum = page,
                type = "0",
                search = ""
            )

            //then
            assertNotNull(result)
            assertTrue(result.items.size == 20)
            assertTrue(result.items[1].medicineId == 2)
            assertTrue(result.items.first().medicineId == 1)
            assertTrue(result.items.first().arabicMedicineName == "بانادول")
            assertTrue(result.items[1].quantity == 20)

        }
    }

    @Test
    fun getAllSuppliersByAreaAndMedicine_Medicineid3_Return30Suppliers() {
        runBlocking {
            //given
            val areaID = 2
            val medicineID = 3
            val pageSize = 100
            val page = 1
            //when
            val result = warehouseApiService.getAllSuppliersByAreaAndMedicine(
                medicineId = medicineID,
                areaId = areaID,
            )

            //then
            assertNotNull(result)
            assertTrue(result.size == 32)
            assertTrue(result[4].warehouseId == 21)
            assertTrue(result.first().warehouseId == 2)
            assertTrue(result.first().quantity == 10)
            assertTrue(result.first().finalPrice == 38.4750)
            assertTrue(result[5].finalPrice == 34.2000)
            assertTrue(result[5].warehHouseName == "xoxo")

        }
    }


}