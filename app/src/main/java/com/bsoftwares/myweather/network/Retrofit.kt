package com.bsoftwares.myweather.network

import com.bsoftwares.myweather.model.Data
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

interface WeateherApi {

    @GET("forecast")
    fun getWeather(@Query("q") conversion : String, @Query("appid") apiKey : String,@Query("units") unit : String) : Deferred<Data>
}