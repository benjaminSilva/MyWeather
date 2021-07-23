package com.bsoftwares.myweather

import android.content.Context
import androidx.room.Room
import com.bsoftwares.myweather.database.WeatherDao
import com.bsoftwares.myweather.database.WeatherDatabase
import com.bsoftwares.myweather.network.WeatherApi
import com.bsoftwares.myweather.repository.RepositoryInterface
import com.bsoftwares.myweather.repository.WeatherRepository
import com.bsoftwares.myweather.utils.BASE_URL
import com.bsoftwares.myweather.utils.DATABASE_NAME
import com.bsoftwares.myweather.utils.DataStoreManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideAPI(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, WeatherDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun dataStoreManager(@ApplicationContext appContext: Context): DataStoreManager =
        DataStoreManager(appContext)

    @Singleton
    @Provides
    fun provideRepository(
        dao: WeatherDao,
        api: WeatherApi,
        dataStore : DataStoreManager
    ) = WeatherRepository(api, dao,dataStore) as RepositoryInterface

    @Singleton
    @Provides
    fun provideDao(
        database: WeatherDatabase
    ) = database.weatherDao()
}