package com.bsoftwares.myweather.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DayDB::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}