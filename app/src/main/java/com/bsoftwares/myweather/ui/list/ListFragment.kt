package com.bsoftwares.myweather.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bsoftwares.myweather.R
import com.bsoftwares.myweather.databinding.FragmentListBinding
import com.bsoftwares.myweather.model.Data
import com.bsoftwares.myweather.network.Network
import com.bsoftwares.myweather.utils.API_KEY
import com.bsoftwares.myweather.utils.UNIT_SYSTEM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    val adapter = WeatherAdapter(WeatherListener { day ->
        findNavController().navigate(R.id.action_SecondFragment_to_detailsFragment,bundleOf(Pair("day",day)))
    })

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