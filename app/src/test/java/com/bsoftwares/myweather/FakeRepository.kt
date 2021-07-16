package com.bsoftwares.myweather

import android.util.Log
import com.bsoftwares.myweather.database.DayDB
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

        if (city == "Boston")
            emit(ApiState.Success(listOf(
                DayDB("Boston",72F,77F,"Cloudy","Few Clouds","Tomorrow",1),
                DayDB("Boston",72F,77F,"Cloudy","Few Clouds","Tomorrow",2),
                DayDB("Boston",72F,77F,"Cloudy","Few Clouds","Tomorrow",3),
                DayDB("Boston",72F,77F,"Cloudy","Few Clouds","Tomorrow",4),
                DayDB("Boston",72F,77F,"Cloudy","Few Clouds","Tomorrow",5),
                DayDB("Boston",72F,77F,"Cloudy","Few Clouds","Tomorrow",6),
                DayDB("Boston",72F,77F,"Cloudy","Few Clouds","Tomorrow",7)
            )))
        else
            emit(ApiState.Error("Error"))
    }
}