package com.example.shop.data.dtos.response

import lombok.Data
import lombok.NoArgsConstructor


data class JwtResponse(
    val token: String,
    val id: String,
    val username: String,
    val email: String?,
    val roles: List<String>,
    private val type: String = "Bearer"
)
