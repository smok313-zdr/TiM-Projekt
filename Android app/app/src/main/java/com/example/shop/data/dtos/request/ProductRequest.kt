package com.example.shop.data.dtos.request

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

data class ProductRequest (
    val description: String,
    val price: Int,
    val amount: Int,
    val picture: String,
    val nameOfProduct: String
)