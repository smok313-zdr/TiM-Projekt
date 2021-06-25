package com.example.shop.data.dtos.request

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

data class UserRequest (
    val surname: String,
    val name: String,
    val email: String,
)