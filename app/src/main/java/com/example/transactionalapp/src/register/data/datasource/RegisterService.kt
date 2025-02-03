package com.example.transactionalapp.src.register.data.datasource

import com.example.transactionalapp.src.register.data.model.CreateUserRequest
import com.example.transactionalapp.src.register.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface RegisterService {
    @POST("/api/register")
    suspend fun registerUser(@Body request: CreateUserRequest): Response<UserDTO>
}