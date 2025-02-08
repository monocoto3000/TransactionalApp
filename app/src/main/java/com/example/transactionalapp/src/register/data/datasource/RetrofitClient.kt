package com.example.transactionalapp.src.register.data.datasource

import com.example.transactionalapp.core.RetrofitClient

object RegisterApi {
    val api: RegisterService by lazy {
        RetrofitClient.instance.create(RegisterService::class.java)
    }
}
