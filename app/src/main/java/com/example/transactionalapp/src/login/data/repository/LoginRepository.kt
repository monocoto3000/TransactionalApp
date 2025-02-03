package com.example.transactionalapp.src.login.data.repository

import com.example.transactionalapp.src.login.data.datasource.LoginService
import com.example.transactionalapp.src.login.data.model.LoginRequest

class LoginRepository(private val api: LoginService) {
    suspend fun loginUser(username: String, password: String) =
        api.loginUser(LoginRequest(username, password))
}