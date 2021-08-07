package com.example.apimvvm.network

import com.example.apimvvm.model.ShowResult
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowService {
    @GET("search/shows")
    suspend fun searchShows(@Query("q") searchKey:String):List<ShowResult>

}