package com.example.transactionalapp.src.viewProduct.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.transactionalapp.src.viewProduct.data.model.ViewProductDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewProductUi(viewModel: ViewProductViewModel, navController: NavController) {
    val products by viewModel.products.collectAsState()
    LaunchedEffect(Unit) { viewModel.loadProducts() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Productos") },
                actions = {
                    IconButton(onClick = { navController.navigate("create_product") }) {
                        Icon(Icons.Default.Add, contentDescription = "Create product")
                    }
                    IconButton(onClick = { navController.navigate("login") }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Log out")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(products) { product ->
                ProductCard(product)
            }
        }
    }
}

@Composable
fun ProductCard(product: ViewProductDTO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = product.description,
            )
            Text(
                text = "Precio: $${product.price}",
            )
        }
    }
}
