package com.bsoftwares.myweather.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertDays(day : List<DayDB>)

    @Query("SELECT * FROM daydb")
    abstract fun getAllDays(): Flow<List<DayDB>>
}