package com.bsoftwares.myweather.repository

import android.util.Log
import com.bsoftwares.myweather.model.ApiState
import com.bsoftwares.myweather.network.WeateherApi
import com.bsoftwares.myweather.utils.API_KEY
import com.bsoftwares.myweather.utils.UNIT_SYSTEM
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val weatherApi : WeateherApi) {

     fun loadDataFlow(city: String) = flow {
        emit(ApiState.Loading)
        try {
            val data = weatherApi.getWeather(city, API_KEY, UNIT_SYSTEM).await()
            emit(ApiState.Success(data))
        } catch (e: HttpException) {
            emit(ApiState.Error(e.message()))
            Log.e("ERROR", e.message.toString())
        }
    }
}

