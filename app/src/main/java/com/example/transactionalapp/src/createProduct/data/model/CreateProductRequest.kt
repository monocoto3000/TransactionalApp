package com.example.transactionalapp.src.createProduct.data.model

data class CreateProductRequest(
    val name: String,
    val description: String,
    val price: Int
)