package com.example.transactionalapp.src.register.domain

import com.example.transactionalapp.src.register.data.repository.RegisterRepository
import com.example.transactionalapp.src.register.data.model.UserDTO
import retrofit2.Response

class CreateUserUseCase(private val repository: RegisterRepository) {
    suspend fun execute(username: String, email: String, password: String): Response<UserDTO> {
        return repository.registerUser(username, email, password)
    }
}