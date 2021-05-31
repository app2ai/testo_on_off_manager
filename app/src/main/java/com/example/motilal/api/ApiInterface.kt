package com.kldep.app.api

import com.example.motilal.models.Repositories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("users/app2ai/repos")
    fun getAllRepoApi(): Call<Repositories>

    @GET("data/2.5/weather")
    fun weatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKay: String
    ): Call<Any>
}