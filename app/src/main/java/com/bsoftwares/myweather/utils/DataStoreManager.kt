package com.bsoftwares.myweather.utils

import android.content.Context
import android.provider.Settings
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("settings")

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext appContext: Context) {

    companion object {
        private val TIME_KEY = longPreferencesKey("TIME_KEY")
    }

    private val settingsDataStore = appContext.dataStore

    suspend fun setCurrentTime(currentTime : Long) {
        settingsDataStore.edit { settings ->
            settings[TIME_KEY] = currentTime
        }
    }

    val lastTimeChecked: Flow<Long> = settingsDataStore.data.map {
        it[TIME_KEY] ?: 0L
    }

}