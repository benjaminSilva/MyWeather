package com.bsoftwares.myweather.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bsoftwares.myweather.R
import com.bsoftwares.myweather.databinding.FragmentListBinding
import com.bsoftwares.myweather.ui.first.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModelFrag: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentListBinding.inflate(inflater, container, false).apply {
        activity?.title = viewModelFrag.city.value
        lifecycleOwner = viewLifecycleOwner
        viewModel = viewModelFrag
    }.root

}