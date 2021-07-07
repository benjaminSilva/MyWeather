package com.bsoftwares.myweather.ui.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bsoftwares.myweather.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!



    private val viewModelFrag : FirstFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFirstBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = viewLifecycleOwner
        viewModel = viewModelFrag
    }.root


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}