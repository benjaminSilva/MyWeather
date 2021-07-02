package com.bsoftwares.myweather.utils

import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bsoftwares.myweather.ApiState
import com.bsoftwares.myweather.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("loadImage")
fun ImageView.loadImage(weather : String) {
    when (weather){
        "overcast clouds" -> setImageResource(R.drawable.cloudy3)
        "broken clouds" -> setImageResource(R.drawable.cloudy2)
        "light rain" -> setImageResource(R.drawable.rainy)
        "moderate rain" -> setImageResource(R.drawable.rainy2)
        "clear sky" -> setImageResource(R.drawable.sunny)
        "few clouds" -> setImageResource(R.drawable.cloudy)
        else -> setImageResource(R.drawable.sunny)
    }
}

@BindingAdapter("errorMessage")
fun TextInputLayout.errorMessage(state : ApiState){
    isErrorEnabled = state is ApiState.Error
    when(state){
        is ApiState.Error -> error = context.getString(R.string.error_existing_city)
        else -> error = null
    }
}