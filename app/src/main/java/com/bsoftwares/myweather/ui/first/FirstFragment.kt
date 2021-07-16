package com.bsoftwares.myweather.ui.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bsoftwares.myweather.R
import com.bsoftwares.myweather.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FirstFragment : Fragment() {

    private val viewModelFrag : WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFirstBinding.inflate(inflater, container, false).apply {
        activity?.title = getString(R.string.app_name)
        lifecycleOwner = viewLifecycleOwner
        viewModel = viewModelFrag
    }.root

}