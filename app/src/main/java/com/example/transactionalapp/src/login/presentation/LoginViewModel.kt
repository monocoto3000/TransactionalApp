package com.example.transactionalapp.src.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transactionalapp.src.login.domain.LoginUseCase
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

open class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var loginSuccess = mutableStateOf<Boolean?>(null)
        private set

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = loginUseCase.execute(username, password)
                if (response.isSuccessful) {
                    println("✅ Usuario ingresó correctamente: ${response.body()}")
                    loginSuccess.value = true
                } else {
                    println("❌ Error con el inicio de sesión: ${response.errorBody()?.string()}")
                    loginSuccess.value = false
                }
            } catch (e: Exception) {
                println("⚠️ Error en la solicitud: ${e.message}")
                loginSuccess.value = false
            }
        }
    }

    fun resetLoginState() {
        loginSuccess.value = null
    }
}