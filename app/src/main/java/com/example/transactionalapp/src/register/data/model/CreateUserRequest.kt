package com.example.transactionalapp.src.register.data.model

data class CreateUserRequest(
    val username: String,
    val email: String,
    val password: String
)