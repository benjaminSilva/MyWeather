package com.bsoftwares.myweather.model

import android.os.Parcelable
import androidx.lifecycle.Transformations.map
import com.bsoftwares.myweather.database.DayDB
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

fun Data.toEntity() : List<DayDB> {
    return list.mapIndexed{ index, data ->
        DayDB(
            city = city.name,
            temp = data.main.temp,
            feels_like = data.main.feels_like,
            main = data.weather[0].main,
            description = data.weather[0].description,
            dt = index,
            dt_txt = data.dt_txt
        )
    }
}