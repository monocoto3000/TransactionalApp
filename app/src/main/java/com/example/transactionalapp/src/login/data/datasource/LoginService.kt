package com.example.transactionalapp.src.login.data.datasource

import com.example.transactionalapp.src.login.data.model.LoginDTO
import com.example.transactionalapp.src.login.data.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/api/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginDTO>
}