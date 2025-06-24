package com.example.atonce.data.remote.service

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.atonce.data.remote.dto.authentication.LoginRequestDto
import com.example.atonce.di.networkModule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
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


}