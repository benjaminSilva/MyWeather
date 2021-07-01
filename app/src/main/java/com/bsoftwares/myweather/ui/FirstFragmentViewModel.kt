package com.bsoftwares.myweather.ui

import android.app.Application
import androidx.lifecycle.*
import com.bsoftwares.myweather.ApiState
import com.bsoftwares.myweather.repository.Repository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class FirstFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    val repository = Repository()

    private val dataState = MutableLiveData<ApiState>()
    val dataStateLD: LiveData<ApiState>
        get() = dataState


    fun getWeatherFlow(city: String) {
        viewModelScope.launch {
            repository.loadDataFlow(city).collect {
                dataState.postValue(it)
            }
        }
    }

    fun apiSleep() {
        dataState.postValue(ApiState.Sleep)
    }

    /*fun getWeather(city: String) {
        viewModelScope.launch {
            repository.loadData(city)
        }
    }*/

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FirstFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FirstFragmentViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }
}