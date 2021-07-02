package com.bsoftwares.myweather.ui.first

import android.app.Application
import androidx.lifecycle.*
import com.bsoftwares.myweather.ApiState
import com.bsoftwares.myweather.repository.Repository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class FirstFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    val repository = Repository()

    private val dataState = MutableLiveData<ApiState>(ApiState.Sleep)
    val dataStateLD: LiveData<ApiState>
        get() = dataState

    val city = MutableLiveData<String>()

    val errorMessage = MutableLiveData<String>()


    fun getWeatherFlow(city : String) {
        viewModelScope.launch {
            repository.loadDataFlow(city).collect {
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