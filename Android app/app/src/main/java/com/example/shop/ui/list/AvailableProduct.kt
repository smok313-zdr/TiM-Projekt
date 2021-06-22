package com.example.shop.ui.list

import android.os.Parcelable
import java.io.Serializable


internal class Employee

data class AvailableProduct(
    val id: String,
    val nameOfProduct: String,
    val description: String,
    val price: Int,
    val amount: Int,
    val picture: String,
    var amountToBuy: Int
)
