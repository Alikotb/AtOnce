package com.example.atonce.data.remote.service

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.atonce.data.remote.dto.RegisterRequestDto
import com.example.atonce.data.remote.dto.authentication.LoginRequestDto
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue


@RunWith(AndroidJUnit4::class)
class AuthApiServiceTest : KoinTest {


    private val authApiService: AuthApiService by inject()


    @Test
    fun testLogin_Success(){
        runBlocking {

            //given
            val request = LoginRequestDto(
                email = "testUser@test.com",
                password = "Aa12345678"
            )

            //when

            val response = authApiService.login(request)

            Log.e("TEST_TAG", "testLogin_Success: $response", )

            //then
            assertNotNull(response)
            assertTrue(response.success == true)
            assertNotNull(response.message)
            assertNotNull(response.token)
            assertNotNull(response.pharmacy)
            assertNotNull(response.pharmacy.id)
        }
    }

    @Test
    fun testLogin_Fail_WrongPassword(){
        runBlocking {
            //given
            val request = LoginRequestDto(
                email = "testUser@test.com",
                password = "Aa123456789"
            )

            //when

            val response = authApiService.login(request)

            Log.e("TEST_TAG", "testLogin_Fail_WrongPassword: $response", )

            //then
            assertNotNull(response)
            assertTrue(response.success == false)
            assertNotNull(response.message)
            assertNull(response.token)
            assertNotNull(response.pharmacy)
            assertNull(response.pharmacy.id)

        }
    }

    @Test
    fun testLogin_Fail_MissingEmail(){
        runBlocking {
            //given
            val request = LoginRequestDto(
                email = "",
                password = "Aa12345678"
            )

            //when
            val response = authApiService.login(request)

            Log.e("TEST_TAG", "testLogin_Fail_MissingEmail: $response")

            //then
            assertNotNull(response)
            assertTrue(response.success == false)
            assertNotNull(response.message)
            assertNull(response.token)
            assertNotNull(response.pharmacy)
            assertNull(response.pharmacy.id)
        }
    }

    @Test
    fun testGetAllGovernorates_Success(){
        runBlocking {

            //when
            val response = authApiService.getAllGovernorates()
            println("Governorates Response: $response")

            // then
            assertNotNull(response)
            assertTrue(response.isNotEmpty(), "Governorates list should not be empty")

            val firstGovernorate = response.first()
            assertNotNull(firstGovernorate.id)
            assertNotNull(firstGovernorate.name)
        }
    }

    @Test
    fun testGetAreasByGovernorateId_Success(){
        runBlocking {
            //given
            val governorateId = 1
            //when
            val response = authApiService.getAreasByGovernorateId(governorateId)
            println("Areas Response: $response")
            //then
            assertNotNull(response)
            assertTrue(response.isNotEmpty(), "Areas list should not be empty")
            val firstArea = response.first()
            assertNotNull(firstArea.id)
            assertNotNull(firstArea.name)
        }
    }

    @Test
    fun testGetAreasByGovernorateId_InvalidGovernorateId_ReturnsEmptyList(){
        runBlocking {
            //given
            val invalidGovernorateId = 999
            //when
            val response = authApiService.getAreasByGovernorateId(invalidGovernorateId)
            println("Areas Response: $response")
            //then
            assertNotNull(response)
            assertTrue(response.isEmpty(), "Areas list should be empty for invalid governorateId")
        }
    }

    @Test
    fun testRegister_Success(){
        runBlocking {

            //given
            val request = RegisterRequestDto(
                address = "Test Address",
                areaId = 1,
                confirmPassword = "Aa12345678",
                email = "newuser${System.currentTimeMillis()}@test.com",
                governate = "القاهرة",
                name = "Test Pharmacy",
                password = "Aa12345678",
                phoneNumber = "0123456789",
                representativeCode = "RYHOZF",
                userName = "testuser${System.currentTimeMillis()}"
            )

            //when
            val response = authApiService.register(request)
            Log.d("TAG", "testRegister_Success: $response")

            //then
            assertNotNull(response)
            assertTrue(response.success == true)
            assertNotNull(response.message)
        }
    }

    @Test
    fun testRegister_Fail_InvalidGovernorateId(){
        runBlocking {

            //given
            val request = RegisterRequestDto(
                address = "Test Address",
                areaId = 1,
                confirmPassword = "Aa12345678",
                email = "newuser${System.currentTimeMillis()}@test.com",
                governate = "باريس",
                name = "Test Pharmacy",
                password = "Aa12345678",
                phoneNumber = "0123456789",
                representativeCode = "RYHOZF",
                userName = "testuser${System.currentTimeMillis()}"
            )

            //when
            val response = authApiService.register(request)
            Log.d("TAG", "testRegister_Success: $response")

            //then
            assertNotNull(response)
            assertTrue(!response.success)
            assertNotNull(response.message)
        }
    }

    @Test
    fun testRegister_Fail_ExistingEmail(){
        runBlocking {

            //given
            val request = RegisterRequestDto(
                address = "Test Address",
                areaId = 1,
                confirmPassword = "Aa12345678",
                email = "testUser@test.com",
                governate = "القاهرة",
                name = "Test Pharmacy",
                password = "Aa12345678",
                phoneNumber = "0123456789",
                representativeCode = "REP123",
                userName = "existingusername"
            )

            //when
            val response = authApiService.register(request)
            println("Register Existing Email Fail Response: $response")

            //then
            assertNotNull(response)
            assertTrue(!response.success)
            assertNotNull(response.message)
        }
    }

    @Test
    fun testRegister_Fail_PasswordMismatch() {
        runBlocking {

            //given
            val request = RegisterRequestDto(
                address = "Test Address",
                areaId = 1,
                confirmPassword = "Aa12345678",
                email = "mismatch${System.currentTimeMillis()}@test.com",
                governate = "القاهرة",
                name = "Test Pharmacy",
                password = "DifferentPass123",
                phoneNumber = "0123456789",
                representativeCode = "REP123",
                userName = "mismatchuser${System.currentTimeMillis()}"
            )

            //when
            val response = authApiService.register(request)
            println("Register Password Mismatch Response: $response")

            //then
            assertNotNull(response)
            assertTrue(!response.success)
            assertNotNull(response.message)
        }
    }

    @Test
    fun testRegister_Fail_MissingEmail() {
        runBlocking {

            //given
            val request = RegisterRequestDto(
                address = "Test Address",
                areaId = 1,
                confirmPassword = "Aa12345678",
                email = "",
                governate = "القاهرة",
                name = "Test Pharmacy",
                password = "Aa12345678",
                phoneNumber = "0123456789",
                representativeCode = "REP123",
                userName = "missingemail${System.currentTimeMillis()}"
            )

            //when
            val response = authApiService.register(request)
            println("Register Missing Email Response: $response")

            //then
            assertNotNull(response)
            assertTrue(!response.success)
            assertNotNull(response.message)
        }
    }





}