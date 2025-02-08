package com.example.transactionalapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.transactionalapp.core.AppNavigation
import com.example.transactionalapp.src.createProduct.data.datasource.CreateProductApi
import com.example.transactionalapp.src.createProduct.data.repository.ProductRepository
import com.example.transactionalapp.src.createProduct.domain.CreateProductUseCase
import com.example.transactionalapp.src.createProduct.presentation.ProductViewModel
import com.example.transactionalapp.src.login.data.datasource.LoginApi
import com.example.transactionalapp.src.login.data.repository.LoginRepository
import com.example.transactionalapp.src.login.domain.LoginUseCase
import com.example.transactionalapp.src.login.presentation.LoginViewModel
import com.example.transactionalapp.src.register.data.datasource.RegisterApi
import com.example.transactionalapp.src.register.data.repository.RegisterRepository
import com.example.transactionalapp.src.register.domain.CreateUserUseCase
import com.example.transactionalapp.src.register.presentation.RegisterViewModel
import com.example.transactionalapp.src.viewProduct.data.datasource.ViewProductApi
import com.example.transactionalapp.src.viewProduct.data.repository.ViewProductRepository
import com.example.transactionalapp.src.viewProduct.domain.ViewProductUseCase
import com.example.transactionalapp.src.viewProduct.presentation.ViewProductViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val registerViewModel = RegisterViewModel(CreateUserUseCase(RegisterRepository(RegisterApi.api)))
            val createProductViewModel = ProductViewModel(CreateProductUseCase(ProductRepository(CreateProductApi.api)))
            val loginViewModel = LoginViewModel(LoginUseCase(LoginRepository(LoginApi.api)))
            val viewProductViewModel = ViewProductViewModel(ViewProductUseCase(ViewProductRepository(ViewProductApi.api)))

            AppNavigation(
                navController = navController,
                loginViewModel = loginViewModel,
                registerViewModel = registerViewModel,
                createProductViewModel = createProductViewModel,
                viewProductViewModel = viewProductViewModel
            )
        }
    }
}
