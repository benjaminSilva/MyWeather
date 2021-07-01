package com.bsoftwares.myweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bsoftwares.myweather.R
import com.bsoftwares.myweather.databinding.FragmentDetailsBinding
import com.bsoftwares.myweather.databinding.FragmentListBinding
import com.bsoftwares.myweather.model.Day

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val day = arguments?.getParcelable<Day>("day")
        binding.data = day
    }
}