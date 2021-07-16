package com.bsoftwares.myweather.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import com.bsoftwares.myweather.database.WeatherDao
import com.bsoftwares.myweather.model.ApiState
import com.bsoftwares.myweather.model.toEntity
import com.bsoftwares.myweather.network.WeatherApi
import com.bsoftwares.myweather.utils.API_KEY
import com.bsoftwares.myweather.utils.DataStoreManager
import com.bsoftwares.myweather.utils.SHAREDPREF_LAST_TIME
import com.bsoftwares.myweather.utils.UNIT_SYSTEM
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val databaseDao: WeatherDao,
    private val dataStore : DataStoreManager
) : RepositoryInterface {

    override suspend fun loadDataFlow(city: String) = flow {
        emit(ApiState.Loading)
        try {
            dataStore.lastTimeChecked.collect { currentTime ->
                if (Calendar.getInstance().timeInMillis - currentTime> 600000) {
                    dataStore.setCurrentTime(Calendar.getInstance().timeInMillis)
                    val data = weatherApi.getWeather(city, API_KEY, UNIT_SYSTEM).await()
                    databaseDao.insertDays(data.toEntity())
                }
                databaseDao.getAllDays().collect { data ->
                    emit(ApiState.Success(data))
                }
            }
        } catch (e: HttpException) {
            emit(ApiState.Error(e.message()))
            Log.e("ERROR", e.message.toString())
        }
    }
}

