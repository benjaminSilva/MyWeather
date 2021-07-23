package com.bsoftwares.myweather.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsoftwares.myweather.database.DayDB
import com.bsoftwares.myweather.model.ApiState
import com.bsoftwares.myweather.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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