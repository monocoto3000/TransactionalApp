package com.example.transactionalapp.src.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.transactionalapp.src.register.domain.CreateUserUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RegisterViewModel(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _passwordLength = MutableLiveData(false)
    val passwordLength: LiveData<Boolean> = _passwordLength

    private val _passwordHasUppercase = MutableLiveData(false)
    val passwordHasUppercase: LiveData<Boolean> = _passwordHasUppercase

    private val _passwordHasLowercase = MutableLiveData(false)
    val passwordHasLowercase: LiveData<Boolean> = _passwordHasLowercase

    private val _passwordHasNumber = MutableLiveData(false)
    val passwordHasNumber: LiveData<Boolean> = _passwordHasNumber

    private val _isPasswordValid = MutableLiveData(false)
    val isPasswordValid: LiveData<Boolean> = _isPasswordValid

    private val _registrationResult = MutableLiveData<RegistrationResult>()
    val registrationResult: LiveData<RegistrationResult> = _registrationResult

    fun onUsernameChanged(newUsername: String) {
        _username.value = newUsername
    }

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword

        val hasLength = newPassword.length >= 8
        val hasUppercase = newPassword.any { it.isUpperCase() }
        val hasLowercase = newPassword.any { it.isLowerCase() }
        val hasNumber = newPassword.any { it.isDigit() }

        _passwordLength.value = hasLength
        _passwordHasUppercase.value = hasUppercase
        _passwordHasLowercase.value = hasLowercase
        _passwordHasNumber.value = hasNumber

        _isPasswordValid.value = hasLength && hasUppercase && hasLowercase && hasNumber
    }

    fun resetRegistrationResult() {
        _registrationResult.value = null
    }

    fun resetRegistrationForm() {
        _username.value = ""
        _email.value = ""
        _password.value = ""
        _registrationResult.value = null
        _passwordLength.value = false
        _passwordHasUppercase.value = false
        _passwordHasLowercase.value = false
        _passwordHasNumber.value = false
        _isPasswordValid.value = false
    }

    fun registerUser() {
        viewModelScope.launch {
            val currentUsername = _username.value ?: ""
            val currentEmail = _email.value ?: ""
            val currentPassword = _password.value ?: ""

            if (currentUsername.isEmpty() || currentEmail.isEmpty() || currentPassword.isEmpty()) {
                _registrationResult.value = RegistrationResult.Error("Missing data")
                return@launch
            }

            if (!_isPasswordValid.value!!) {
                _registrationResult.value = RegistrationResult.Error("The password is not valid")
                return@launch
            }

            try {
                val response = createUserUseCase.execute(currentUsername, currentEmail, currentPassword)

                if (response.isSuccessful) {
                    _registrationResult.value = RegistrationResult.Success
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    _registrationResult.value = RegistrationResult.Error(errorBody)
                }
            } catch (e: Exception) {
                _registrationResult.value = RegistrationResult.Error("Register error: ${e.message}")
            }
        }
    }
}

sealed class RegistrationResult {
    object Success : RegistrationResult()
    data class Error(val message: String) : RegistrationResult()
}

