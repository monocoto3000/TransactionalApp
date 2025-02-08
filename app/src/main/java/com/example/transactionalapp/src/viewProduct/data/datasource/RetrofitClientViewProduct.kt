package com.example.transactionalapp.src.viewProduct.data.datasource

import com.example.transactionalapp.core.RetrofitClient

object ViewProductApi {
    val api: ViewProductService by lazy {
        RetrofitClient.instance.create(ViewProductService::class.java)
    }
}
