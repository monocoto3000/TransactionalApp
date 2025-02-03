package com.example.transactionalapp.src.viewProduct.data.repository

import com.example.transactionalapp.src.viewProduct.data.datasource.ViewProductService
import com.example.transactionalapp.src.viewProduct.data.model.ViewProductDTO

class ViewProductRepository(private val api: ViewProductService) {
    suspend fun getProducts(): List<ViewProductDTO> {
        return api.viewProducts()
    }
}