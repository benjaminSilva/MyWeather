package com.bsoftwares.myweather.ui.first

import android.app.Application
import androidx.lifecycle.*
import com.bsoftwares.myweather.database.DayDB
import com.bsoftwares.myweather.model.ApiState
import com.bsoftwares.myweather.model.Day
import com.bsoftwares.myweather.repository.RepositoryInterface
import com.bsoftwares.myweather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepo: RepositoryInterface) :
    ViewModel() {

    private val dataState = MutableLiveData<ApiState<List<DayDB>>>(ApiState.Sleep)
    val dataStateLD: LiveData<ApiState<List<DayDB>>>
        get() = dataState

    val days = MutableLiveData<List<DayDB>>()

    val day = MutableLiveData<DayDB>()

    val city = MutableLiveData<String>()

    val updateAfterSuccess : Function1<List<DayDB>,Unit> = { data ->
        apiSleep()
        days.postValue(data)
    }

    val selectingDay : Function1<DayDB,Unit> = { selectedDay ->
        day.postValue(selectedDay)
    }

    fun getWeatherFlow(city: String) {
        viewModelScope.launch {
            Calendar.getInstance().time
            weatherRepo.loadDataFlow(city).collect {
                dataState.postValue(it)
            }
        }
    }

    fun apiSleep() {
        dataState.postValue(ApiState.Sleep)
    }
}