package com.bsoftwares.myweather.network

import com.bsoftwares.myweather.model.Data
import com.bsoftwares.myweather.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("forecast")
    fun getWeather(@Query("q") conversion : String, @Query("appid") apiKey : String,@Query("units") unit : String) : Deferred<Data>
}

object Network {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val getApi = retrofit.create(RetrofitInterface::class.java)
}
