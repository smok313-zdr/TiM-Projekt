package com.example.shop.data.dtos.response

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor



data class ProductResponse (
    val id: String,
    val nameOfProduct: String,
    val description: String,
    val price: Int,
    val amount: Int,
    val picture: String,
)