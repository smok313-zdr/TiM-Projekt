package com.example.shop.data.dtos.response

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

data class UserResponse (
    val id: String,
    val email: String,
    val surname: String,
    val name: String,
)