package com.kldep.app.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ClientHttpRetro {
    private const val BASE_URL = "https://api.github.com/"
    private val okHttpClient : OkHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

    fun getRetrofit(baseUrl: String = BASE_URL): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}