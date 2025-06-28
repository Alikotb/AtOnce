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
class MedicineApiServiceTest : KoinTest{

    private val medicineApiService: MedicineApiService by inject()


    @Test
    fun searchMedicinesByNameAndArea_AreaId3SearchAugm_returnAugmentin(){
        runBlocking {
            //given
            val areaId=3
            val searchText = "Augm"

            //when
            var result =  medicineApiService.searchMedicinesByNameAndArea(areaId = areaId, search = searchText, page = 1, type = "0", pageSize = 1)

            //then
            assertNotNull(result)
            assertTrue(result.items.first().medicineName=="Augmentin")
            assertTrue(result.items.first().finalPrice==84.0)
            assertTrue(result.items.first().warehouseNameOfMaxDiscount == "eva pharma")


        }

    }
}