package com.example.shop.data.dtos.request

data class AddUserRequest (
    private val username: String,
    private val email: String,
    private val role: Set<String>,
    private val password: String,
    private val surname: String,
    private val name: String,
)