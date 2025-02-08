package com.example.transactionalapp.src.createProduct.data.datasource

import com.example.transactionalapp.core.RetrofitClient

object CreateProductApi {
    val api: CreateProductService by lazy {
        RetrofitClient.instance.create(CreateProductService::class.java)
    }
}