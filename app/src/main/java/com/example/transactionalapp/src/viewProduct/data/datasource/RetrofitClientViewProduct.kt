package com.example.transactionalapp.src.viewProduct.data.datasource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientViewProduct {
    private const val BASE_URL = "http://192.168.0.182:3000"

    val api: ViewProductService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ViewProductService::class.java)
    }
}