package com.bsoftwares.myweather.utils

import android.widget.EditText
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bsoftwares.myweather.model.ApiState
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

@BindingAdapter("errorMessage", "listener")
fun TextInputLayout.errorMessage(state : ApiState, listener: () -> Unit){
    isErrorEnabled = state is ApiState.Error
    when(state){
        is ApiState.Success -> {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundleOf("days" to state.data))
            listener.invoke()
        }
        is ApiState.Error -> error = context.getString(R.string.error_existing_city)
    }
}

@BindingAdapter("inputLayout")
fun EditText.changeListener(inputLayout : TextInputLayout){
    addTextChangedListener {
        inputLayout.error = null
    }
}
