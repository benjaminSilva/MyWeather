package com.bsoftwares.myweather.model

import com.bsoftwares.myweather.database.DayDB

sealed class ApiState<out T> {
    data class Success<out T: Any>(val data: T): ApiState<T>()
    data class Error(val msg: String) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
    object Sleep : ApiState<Nothing>()
}