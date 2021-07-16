package com.bsoftwares.myweather.utils

import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bsoftwares.myweather.R
import com.bsoftwares.myweather.database.DayDB
import com.bsoftwares.myweather.model.ApiState
import com.bsoftwares.myweather.ui.list.WeatherAdapter
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

@BindingAdapter("errorMessage", "listener")
fun TextInputLayout.errorMessage(state : ApiState<List<DayDB>>, listener: (List<DayDB>) -> Unit){
    isErrorEnabled = state is ApiState.Error
    when(state){
        is ApiState.Success -> {
            listener.invoke(state.data)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        is ApiState.Error -> error = context.getString(R.string.error_existing_city)
    }
}

@BindingAdapter("inputLayout")
fun EditText.changeListener(inputLayout : TextInputLayout){
    addTextChangedListener {
        inputLayout.error = null
        inputLayout.isErrorEnabled = false
    }
}

@BindingAdapter("days","listener")
fun RecyclerView.submitList(listDays : List<DayDB>,listener: (DayDB) -> Unit){
    val weatherAdapter = WeatherAdapter { day ->
        listener.invoke(day)
        findNavController().navigate(
            R.id.action_SecondFragment_to_detailsFragment,
        )
    }
    adapter = weatherAdapter
    weatherAdapter.submitList(listDays)
}
