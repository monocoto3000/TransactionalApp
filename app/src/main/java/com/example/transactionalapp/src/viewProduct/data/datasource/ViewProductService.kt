package com.example.transactionalapp.src.viewProduct.data.datasource

import com.example.transactionalapp.src.viewProduct.data.model.ViewProductDTO
import retrofit2.http.GET

interface ViewProductService {
    @GET("api/view-products")
    suspend fun viewProducts(): List<ViewProductDTO>
}