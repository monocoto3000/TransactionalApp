package com.example.transactionalapp.src.viewProduct.domain

import com.example.transactionalapp.src.viewProduct.data.model.ViewProductDTO
import com.example.transactionalapp.src.viewProduct.data.repository.ViewProductRepository

class ViewProductUseCase(private val repository: ViewProductRepository) {
    suspend fun execute(): List<ViewProductDTO> {
        return repository.getProducts()
    }
}