package com.example.transactionalapp.src.register.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegisterUi(
    viewModel: RegisterViewModel,
    navController: NavController
) {
    val username by viewModel.username.observeAsState("")
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")

    val passwordLength by viewModel.passwordLength.observeAsState(false)
    val passwordHasUppercase by viewModel.passwordHasUppercase.observeAsState(false)
    val passwordHasLowercase by viewModel.passwordHasLowercase.observeAsState(false)
    val passwordHasNumber by viewModel.passwordHasNumber.observeAsState(false)
    val isPasswordValid by viewModel.isPasswordValid.observeAsState(false)

    val registrationResult by viewModel.registrationResult.observeAsState()
    var isPasswordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(registrationResult) {
        when (registrationResult) {
            is RegistrationResult.Success -> {
                navController.navigate("view_products")
                viewModel.resetRegistrationResult()
                viewModel.resetRegistrationForm()
            }
            is RegistrationResult.Error -> {
                val errorMessage = (registrationResult as RegistrationResult.Error).message
            }
            null -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Register",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        OutlinedTextField(
            value = username,
            onValueChange = { viewModel.onUsernameChanged(it) },
            label = { Text("Username") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.onEmailChanged(it) },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.onPasswordChanged(it) },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Check else Icons.Default.Close,
                        contentDescription = if (isPasswordVisible) "Hide password" else "Display password"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        PasswordRequirements(
            lengthValid = passwordLength,
            uppercaseValid = passwordHasUppercase,
            lowercaseValid = passwordHasLowercase,
            numberValid = passwordHasNumber
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.registerUser() },
            enabled = isPasswordValid && username.isNotEmpty() && email.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}

@Composable
fun PasswordRequirements(
    lengthValid: Boolean,
    uppercaseValid: Boolean,
    lowercaseValid: Boolean,
    numberValid: Boolean
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        PasswordRequirementItem(
            text = "Password must have at least 8 characters",
            isValid = lengthValid
        )
        PasswordRequirementItem(
            text = "Password must have at least one uppercase character",
            isValid = uppercaseValid
        )
        PasswordRequirementItem(
            text = "Password must have at least one lowercase character",
            isValid = lowercaseValid
        )
        PasswordRequirementItem(
            text = "Password must have at least one number",
            isValid = numberValid
        )
    }
}

@Composable
fun PasswordRequirementItem(
    text: String,
    isValid: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = if (isValid) Icons.Default.Check else Icons.Default.Close,
            contentDescription = null,
            tint = if (isValid) Color.Green else Color.Black,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = if (isValid) Color.Green else Color.Black
        )
    }
}
