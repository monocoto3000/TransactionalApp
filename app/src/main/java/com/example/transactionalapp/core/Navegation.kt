package com.example.transactionalapp.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.transactionalapp.src.createProduct.presentation.CreateProductUi
import com.example.transactionalapp.src.createProduct.presentation.ProductViewModel
import com.example.transactionalapp.src.login.presentation.LoginUi
import com.example.transactionalapp.src.login.presentation.LoginViewModel
import com.example.transactionalapp.src.register.presentation.RegisterUi
import com.example.transactionalapp.src.register.presentation.RegisterViewModel
import com.example.transactionalapp.src.viewProduct.presentation.ViewProductUi
import com.example.transactionalapp.src.viewProduct.presentation.ViewProductViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    createProductViewModel: ProductViewModel,
    viewProductViewModel: ViewProductViewModel
) {
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
