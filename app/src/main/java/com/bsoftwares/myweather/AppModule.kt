package com.bsoftwares.myweather

import com.bsoftwares.myweather.network.WeatherApi
import com.bsoftwares.myweather.repository.RepositoryInterface
import com.bsoftwares.myweather.repository.WeatherRepository
import com.bsoftwares.myweather.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideRepository(weatherApi : WeatherApi) = WeatherRepository(weatherApi) as RepositoryInterface
}