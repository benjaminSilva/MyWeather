package com.bsoftwares.myweather

import com.bsoftwares.myweather.model.Data

sealed class ApiState {
    data class Success(val data: Data) : ApiState()
    data class Error(val msg: String) : ApiState()
    object Loading : ApiState()
    object Sleep : ApiState()
}