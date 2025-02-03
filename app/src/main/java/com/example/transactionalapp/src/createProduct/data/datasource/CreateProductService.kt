package com.example.transactionalapp.src.createProduct.data.datasource

import com.example.transactionalapp.src.createProduct.data.model.CreateProductRequest
import com.example.transactionalapp.src.createProduct.data.model.ProductDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface CreateProductService {
    @POST("/api/products")
    suspend fun createProduct(@Body request: CreateProductRequest): Response<ProductDTO>
}