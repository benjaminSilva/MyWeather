package com.bsoftwares.myweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bsoftwares.myweather.R
import com.bsoftwares.myweather.databinding.FragmentDetailsBinding
import com.bsoftwares.myweather.databinding.FragmentListBinding
import com.bsoftwares.myweather.ui.first.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModelFrag : WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailsBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = viewLifecycleOwner
        data = viewModelFrag.day.value
    }.root

}