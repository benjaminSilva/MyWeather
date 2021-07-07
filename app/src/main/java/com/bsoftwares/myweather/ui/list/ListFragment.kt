package com.bsoftwares.myweather.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bsoftwares.myweather.R
import com.bsoftwares.myweather.databinding.FragmentListBinding
import com.bsoftwares.myweather.model.Data

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val adapter = WeatherAdapter { day ->
        findNavController().navigate(
            R.id.action_SecondFragment_to_detailsFragment,
            bundleOf(Pair("day", day))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDays.adapter = adapter

        val city = arguments?.getParcelable<Data>("days")
        if (city != null) {
            activity?.title = city.city.name
            adapter.submitList(city.list)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}