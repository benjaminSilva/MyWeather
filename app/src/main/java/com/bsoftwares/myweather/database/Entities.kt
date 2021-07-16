package com.bsoftwares.myweather.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class DayDB(
    val city : String,
    val temp : Float,
    val feels_like : Float,
    val main : String,
    val description : String,
    var dt_txt : String,
    @PrimaryKey
    val dt : Int
) : Parcelable