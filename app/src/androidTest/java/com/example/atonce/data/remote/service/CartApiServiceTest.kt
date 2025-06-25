package com.example.atonce.data.remote.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.atonce.data.remote.dto.cart.AddToCartRequestDto
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
class CartApiServiceTest : KoinTest {

    private val apiService : CartApiService by inject()

    @Test
    fun addToCart_wrongRequestParams_failure() {
        runBlocking {
            //given
            val request = AddToCartRequestDto(
                medicineId = -1,
                pharmacyId = -1,
                quantity = -1,
                warehouseId = -1,
                englishMedicineName = "",
                arabicMedicineName = "",
                medicineUrl = "",
                warehouseUrl = "",
                price = 0.0,
                discount = 0.0,
            )

            //when
            val response = apiService.addToCart(request)

            //then
            assertNotEquals(response.success, true)
            assertEquals(response.message, "Medicine not found.")
            assertNull(response.data)

        }
    }

    @Test
    fun addToCart_rightRequestParams_Success() {
        runBlocking {
            //given
            val request = AddToCartRequestDto(
                medicineId = 2,
                pharmacyId = 45,
                quantity = 1,
                warehouseId = 5,
                englishMedicineName = "",
                arabicMedicineName = "",
                medicineUrl = "",
                warehouseUrl = "",
                price = 0.0,
                discount = 0.0,
            )

            val response = apiService.addToCart(request)

            assertEquals(response.success, true)
            assertEquals(response.message, "Item added to cart successfully")
            assertEquals(response.data, true)
        }
    }
}