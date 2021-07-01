package com.bsoftwares.myweather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val list: List<Day>,
    val city: City
) : Parcelable

@Parcelize
data class Day(
    val main: Main,
    val weather: List<Weather>,
    var dt_txt: String,
    val dt : Int
) : Parcelable

@Parcelize
data class City(
    val name: String
) : Parcelable

@Parcelize
data class Main(
    val temp: Float,
    val feels_like: Float
) : Parcelable

@Parcelize
data class Weather(
    val main: String,
    val description: String
) : Parcelable