package com.bsoftwares.myweather.model

sealed class ApiState {
    data class Success(val data: Data) : ApiState()
    data class Error(val msg: String) : ApiState()
    object Loading : ApiState()
    object Sleep : ApiState()
}