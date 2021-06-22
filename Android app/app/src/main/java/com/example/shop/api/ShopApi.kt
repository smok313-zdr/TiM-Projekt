package com.example.shop.api

import com.example.shop.data.dtos.request.AddUserRequest
import com.example.shop.data.dtos.request.CreateAccountRequest
import com.example.shop.data.dtos.request.LoginRequest
import com.example.shop.data.dtos.request.ProductRequest
import com.example.shop.data.dtos.response.JwtResponse
import com.example.shop.data.dtos.response.MakePaymentResponse
import com.example.shop.data.dtos.response.MessageResponse
import com.example.shop.data.dtos.response.ProductResponse
import retrofit2.Response
import retrofit2.http.*

interface ShopApi {
    @GET("api/products")
    suspend fun getProducts(@Header("Authorization") authHeader:String): Response<List<ProductResponse>>

    @POST("api/products")
    suspend fun addProducts(@Body productRequest:ProductRequest, @Header("Authorization") authHeader:String): Response<ProductResponse>

    @GET("api/products/{id}")
    suspend fun getProduct(@Path("id") id:String, @Header("Authorization") authHeader:String): Response<ProductResponse>

    @PUT("api/products/{id}")
    suspend fun editProduct(@Path("id") id:String, @Body productRequest:ProductRequest, @Header("Authorization") authHeader:String): Response<ProductResponse>

    @DELETE("api/products/{id}")
    suspend fun deleteProduct(@Path("id") id:String, @Header("Authorization") authHeader:String): Response<Void>

    @PUT("api/products/buy/{id}")
    suspend fun buyProduct(@Path("id") id:String, @Query("amount") amount:Int, @Header("Authorization") authHeader:String): Response<ProductResponse>

    @POST("api/paypal/android/complete_payment")
    suspend fun completePayment(@Query("payerId") payerId:String, @Query("paymentId") paymentId:String, @Header("Authorization") authHeader:String): Response<Map<String, Any>>

    @POST("api/paypal/android/make_payment")
    suspend fun makePayment(@Query("sum") sum:Double, @Query("token") token:String, @Header("Authorization") authHeader:String): Response<MakePaymentResponse>

    @POST("api/user/sign_in")
    suspend fun signIn(@Body loginRequest: LoginRequest): Response<JwtResponse>

    @POST("api/user/create_account")
    suspend fun createAccount(@Body createAccountRequest: CreateAccountRequest): Response<MessageResponse>

    @POST("api/user/add_user")
    suspend fun addUser(@Body createAccountRequest: AddUserRequest): Response<MessageResponse>

}