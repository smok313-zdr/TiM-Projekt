package com.example.shop.ui.checkout

import com.example.shop.data.dtos.response.ProductResponse

data class BasketProduct(
    val id: String,
    val nameOfProduct: String,
    val price: Int,
    val amount: Int
)
