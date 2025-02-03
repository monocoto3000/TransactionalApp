package com.example.transactionalapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.transactionalapp.src.createProduct.data.datasource.RetrofitClientProduct
import com.example.transactionalapp.src.createProduct.data.repository.ProductRepository
import com.example.transactionalapp.src.createProduct.domain.CreateProductUseCase
import com.example.transactionalapp.src.createProduct.presentation.CreateProductUi
import com.example.transactionalapp.src.viewProduct.presentation.ViewProductUi
import com.example.transactionalapp.src.createProduct.presentation.ProductViewModel
import com.example.transactionalapp.src.login.data.datasource.RetrofitClientLogin
import com.example.transactionalapp.src.login.data.repository.LoginRepository
import com.example.transactionalapp.src.login.domain.LoginUseCase
import com.example.transactionalapp.src.login.presentation.LoginUi
import com.example.transactionalapp.src.login.presentation.LoginViewModel
import com.example.transactionalapp.src.register.data.datasource.RetrofitClient
import com.example.transactionalapp.src.register.data.repository.RegisterRepository
import com.example.transactionalapp.src.register.domain.CreateUserUseCase
import com.example.transactionalapp.src.register.presentation.RegisterUi
import com.example.transactionalapp.src.register.presentation.RegisterViewModel
import com.example.transactionalapp.src.viewProduct.data.datasource.RetrofitClientViewProduct
import com.example.transactionalapp.src.viewProduct.data.repository.ViewProductRepository
import com.example.transactionalapp.src.viewProduct.domain.ViewProductUseCase
import com.example.transactionalapp.src.viewProduct.presentation.ViewProductViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            // Registro
            val registerRepository = RegisterRepository(RetrofitClient.api)
            val registerViewModel = RegisterViewModel(
                CreateUserUseCase(registerRepository),
            )

            // Crear producto
            val productRepository = ProductRepository(RetrofitClientProduct.api)
            val createProductViewModel = ProductViewModel(
                CreateProductUseCase(productRepository)
            )

            // Login
            val loginRepository = LoginRepository(RetrofitClientLogin.api)
            val loginViewModel = LoginViewModel(
                LoginUseCase(loginRepository)
            )

            // Visualizar productos
            val viewProductRepository = ViewProductRepository(RetrofitClientViewProduct.api)
            val viewProductViewModel = ViewProductViewModel(
                ViewProductUseCase(viewProductRepository)
            )

            // Navegacion
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginUi(viewModel = loginViewModel, navController)
                }
                composable("register") {
                    RegisterUi(viewModel = registerViewModel, navController)
                }
                composable("create_product") {
                    CreateProductUi(viewModel = createProductViewModel, navController)
                }
                composable("view_products") {
                    ViewProductUi(viewModel = viewProductViewModel, navController)
                }
            }
        }
    }
}
