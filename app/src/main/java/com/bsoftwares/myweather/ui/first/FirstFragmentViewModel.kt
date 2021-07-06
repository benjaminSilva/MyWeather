package com.bsoftwares.myweather.ui.first

import android.app.Application
import androidx.lifecycle.*
import com.bsoftwares.myweather.model.ApiState
import com.bsoftwares.myweather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class FirstFragmentViewModel @Inject constructor(private val weatherRepo : WeatherRepository) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val dataState = MutableLiveData<ApiState>(ApiState.Sleep)
    val dataStateLD: LiveData<ApiState>
        get() = dataState

    val city = MutableLiveData<String>()

    val errorMessage = MutableLiveData<String>()

    val test : Function0<Unit> = { apiSleep() }


    fun getWeatherFlow(city : String) {
        viewModelScope.launch {
            weatherRepo.loadDataFlow(city).collect {
                dataState.postValue(it)
            }
        }
    }

    init {
        errorMessage.postValue("")
    }

    fun apiSleep() {
        dataState.postValue(ApiState.Sleep)
    }
}