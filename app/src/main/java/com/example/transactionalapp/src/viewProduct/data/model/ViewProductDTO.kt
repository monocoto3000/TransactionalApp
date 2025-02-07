package com.example.transactionalapp.src.viewProduct.data.model

data class ViewProductDTO(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String?
)