package com.example.transactionalapp.src.createProduct.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transactionalapp.src.createProduct.domain.CreateProductUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val createProductUseCase: CreateProductUseCase
) : ViewModel() {

    fun createProduct(name: String, description: String, price: Int, image: String?) {
        viewModelScope.launch {
            try {
                val response = createProductUseCase.execute(name, description, price, image)
                if (response.isSuccessful) {
                    println("✅ Producto creado: ${response.body()}")
                } else {
                    println("❌ Error al crear el producto: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("⚠️ Error en la solicitud: ${e.message}")
            }
        }
    }
}


