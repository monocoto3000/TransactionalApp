package com.example.transactionalapp.src.createProduct.data.datasource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientProduct {
    private const val BASE_URL = "http://192.168.0.109:3000"

    val api: CreateProductService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CreateProductService::class.java)
    }
}