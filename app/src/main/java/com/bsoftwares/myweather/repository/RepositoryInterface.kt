package com.bsoftwares.myweather.repository

import com.bsoftwares.myweather.database.DayDB
import com.bsoftwares.myweather.model.ApiState
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    suspend fun loadDataFlow(city : String) : Flow<ApiState<List<DayDB>>>
}