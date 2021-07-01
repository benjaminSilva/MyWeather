package com.bsoftwares.myweather.repository

import android.util.Log
import com.bsoftwares.myweather.ApiState
import com.bsoftwares.myweather.model.Data
import com.bsoftwares.myweather.network.Network
import com.bsoftwares.myweather.utils.API_KEY
import com.bsoftwares.myweather.utils.UNIT_SYSTEM
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.util.concurrent.Flow

class Repository {

     fun loadDataFlow(city: String) = flow {
        emit(ApiState.Loading)
        try {
            val data = Network.getApi.getWeather(city, API_KEY, UNIT_SYSTEM).await()
            emit(ApiState.Success(data))
        } catch (e: HttpException) {
            emit(ApiState.Error(e.message()))
            Log.e("ERROR", e.message.toString())
        }
    }

    /*suspend fun loadData(city: String) {
        try {
            val data = Network.getApi.getWeather(city, API_KEY, UNIT_SYSTEM).await()
        } catch (e: HttpException) {
            Log.e("ERROR", e.message.toString())
        }
    }*/
}

