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

class ShopRepository(token:String?) {
    var jwt = "Bearer "+token

    suspend fun getProducts(): Response<List<ProductResponse>> {
        return RetrofitInstance.api.getProducts(jwt)
    }

    suspend fun addProducts(productRequest: ProductRequest): Response<ProductResponse> {
        return RetrofitInstance.api.addProducts(productRequest,jwt)
    }

    suspend fun getProduct(id:String): Response<ProductResponse> {
        return RetrofitInstance.api.getProduct(id,jwt)
    }

    suspend fun editProduct(id:String, productRequest: ProductRequest): Response<ProductResponse> {
        return RetrofitInstance.api.editProduct(id,productRequest,jwt)
    }

    suspend fun deleteProduct(id:String): Response<Void> {
        return RetrofitInstance.api.deleteProduct(id,jwt)
    }


    suspend fun buyProduct(id:String, amount:Int): Response<ProductResponse> {
        return RetrofitInstance.api.buyProduct(id,amount,jwt)
    }


    suspend fun completePayment(payerId:String, paymentId:String): Response<Map<String, Any>> {
        return RetrofitInstance.api.completePayment(payerId,paymentId,jwt)
    }

    suspend fun makePayment(sum:Double, token:String): Response<MakePaymentResponse> {
        return RetrofitInstance.api.makePayment(sum,token,jwt)
    }

    suspend fun signIn(loginRequest: LoginRequest): Response<JwtResponse>{
        return RetrofitInstance.api.signIn(loginRequest)
    }

    suspend fun createAccount(createAccountRequest: CreateAccountRequest): Response<MessageResponse>{
        return RetrofitInstance.api.createAccount(createAccountRequest)
    }

    suspend fun addNewUser(userRequest: AddUserRequest): Response<MessageResponse> {
        return RetrofitInstance.api.addUser(userRequest)
    }


}