package com.example.transactionalapp.src.login.data.datasource

import com.example.transactionalapp.core.RetrofitClient

object LoginApi {
    val api: LoginService by lazy {
        RetrofitClient.instance.create(LoginService::class.java)
    }
}
