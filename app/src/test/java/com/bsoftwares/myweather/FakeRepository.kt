package com.bsoftwares.myweather

import android.util.Log
import com.bsoftwares.myweather.model.ApiState
import com.bsoftwares.myweather.model.City
import com.bsoftwares.myweather.model.Data
import com.bsoftwares.myweather.model.Day
import com.bsoftwares.myweather.network.WeatherApi
import com.bsoftwares.myweather.repository.RepositoryInterface
import com.bsoftwares.myweather.utils.API_KEY
import com.bsoftwares.myweather.utils.UNIT_SYSTEM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class FakeRepository() : RepositoryInterface {

    override suspend fun loadDataFlow(city: String) = flow {
        emit(ApiState.Loading)
        /*try {
            val data = weatherApi.getWeather(city, API_KEY, UNIT_SYSTEM).await()
            emit(ApiState.Success(data))
        } catch (e: HttpException) {
            emit(ApiState.Error(e.message()))
            Log.e("ERROR", e.message.toString())
        }*/
        if (city == "Boston")
            emit(ApiState.Success(Data(listOf(),City("Boston"))))
        else
            emit(ApiState.Error("Error"))
    }
}