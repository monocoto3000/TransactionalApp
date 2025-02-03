package com.example.transactionalapp.src.register.data.repository

import com.example.transactionalapp.src.register.data.datasource.RegisterService
import com.example.transactionalapp.src.register.data.model.CreateUserRequest

class RegisterRepository(private val api: RegisterService) {
    suspend fun registerUser(username: String, email: String, password: String) =
        api.registerUser(CreateUserRequest(username, email, password))
}