package com.example.apimvvm.util

import com.example.apimvvm.network.TvShowService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceManager {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.tvmaze.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(TvShowService::class.java)

}