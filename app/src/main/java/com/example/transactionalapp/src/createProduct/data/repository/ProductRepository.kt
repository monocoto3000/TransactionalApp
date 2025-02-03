package com.example.transactionalapp.src.createProduct.data.repository

import com.example.transactionalapp.src.createProduct.data.model.CreateProductRequest
import com.example.transactionalapp.src.createProduct.data.datasource.CreateProductService

class ProductRepository(private val api: CreateProductService) {
    suspend fun createProduct(name: String, description: String, price: Int) =
        api.createProduct(CreateProductRequest(name, description, price))
}