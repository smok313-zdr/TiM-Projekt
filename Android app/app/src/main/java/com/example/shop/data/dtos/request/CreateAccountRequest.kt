package com.example.shop.data.dtos.request

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor


data class CreateAccountRequest(
    val username: String? = null,
    val email: String? = null,
    val password: String? = null,
    val surname: String? = null,
    val name: String? = null,
) {



}