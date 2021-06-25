package com.example.shop.data.dtos.response

import com.google.gson.annotations.SerializedName

data class MakePaymentResponse(
    @SerializedName("redirect_url")
    val redirectUrl: String,
    val status: String,
)
