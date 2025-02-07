package com.example.transactionalapp.src.createProduct.data.model

data class ProductDTO (
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val image: String?
)
