package com.example.transactionalapp.src.createProduct.domain

import com.example.transactionalapp.src.createProduct.data.model.ProductDTO
import com.example.transactionalapp.src.createProduct.data.repository.ProductRepository
import retrofit2.Response

class CreateProductUseCase(private val repository: ProductRepository) {
    suspend fun execute(name: String, description: String, price: Int): Response<ProductDTO> {
        return repository.createProduct(name, description, price)
    }
}