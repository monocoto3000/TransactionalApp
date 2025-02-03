package com.example.transactionalapp.src.login.domain

import com.example.transactionalapp.src.login.data.model.LoginDTO
import com.example.transactionalapp.src.login.data.repository.LoginRepository
import retrofit2.Response

class LoginUseCase(private val repository: LoginRepository) {
    suspend fun execute(username: String, password: String): Response<LoginDTO> {
        return repository.loginUser(username, password)
    }
}