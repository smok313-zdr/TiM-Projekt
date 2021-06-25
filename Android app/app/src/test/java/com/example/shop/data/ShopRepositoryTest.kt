package com.example.shop.data

import androidx.collection.arraySetOf
import com.example.shop.api.ShopRepository
import com.example.shop.data.dtos.request.AddUserRequest
import com.example.shop.data.dtos.request.CreateAccountRequest
import com.example.shop.data.dtos.request.LoginRequest
import com.example.shop.data.dtos.request.ProductRequest
import junit.framework.TestCase
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
class ShopRepositoryTest : TestCase() {

    fun testCreateAccountAndAddProduct() {
        runBlocking {
            val result = ShopRepository(null).addNewUser(
                AddUserRequest(
                    "username",
                    "su@email.com",
                    setOf<String>("admin","user"),
                    "password",
                    "surname",
                    "name"
                )
            )
            val result2 = ShopRepository(null).signIn(
                LoginRequest(
                    "username",
                    "password"
                )
            )
            val shop = ShopRepository(result2.body()?.token)
            val result3 = shop.addProducts(
                ProductRequest(
                    "description0",
                    10,
                    10,
                    "picture0",
                    "product0"
                )
            )
            val result4 = shop.addProducts(
                ProductRequest(
                    "description1",
                    50,
                    50,
                    "picture1",
                    "product1"
                )
            )
            val result5 = shop.addProducts(
                ProductRequest(
                    "description2",
                    100,
                    100,
                    "picture2",
                    "product2"
                )
            )
            print(result3)
            print(result4)
            print(result5)
            assertTrue(result.isSuccessful)
            assertTrue(result2.isSuccessful)
            assertTrue(result2.body()?.username.equals("username"))
            assertTrue(result3.isSuccessful)
            assertTrue(result4.isSuccessful)
            assertTrue(result5.isSuccessful)
            assertTrue(result3.body()?.id != null)
            assertTrue(result4.body()?.id != null)
            assertTrue(result5.body()?.id != null)
        }
    }

    fun testCreateAccountAndPay() {
        runBlocking {
            val result = ShopRepository(null).addNewUser(
                AddUserRequest(
                    "username",
                    "su@email.com",
                    setOf<String>("admin","user"),
                    "password",
                    "surname",
                    "name"
                )
            )
            val result2 = ShopRepository(null).signIn(
                LoginRequest(
                    "username",
                    "password"
                )
            )
            val token = result2.body()?.token
            val shop = ShopRepository(token)
            val result3 = shop.makePayment(
                100.0,token!!
            )
            print(result3)
//           val result4 = shop.cp
//            val result4 = shop.completePayment()

            print(result3.body())
            print(result3.body()?.redirectUrl)
//            print(result4)
//            print(result5)
            assertTrue(result.isSuccessful)
            assertTrue(result2.isSuccessful)
            assertTrue(result2.body()?.username.equals("username"))
            assertTrue(result3.isSuccessful)
//            assertTrue(result4.isSuccessful)
//            assertTrue(result5.isSuccessful)
//            assertTrue(result3.body().id != null)
//            assertTrue(result4.body()?.id != null)
//            assertTrue(result5.body()?.id != null)
        }
    }

}