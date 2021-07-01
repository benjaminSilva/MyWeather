package com.bsoftwares.myweather.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bsoftwares.myweather.ApiState
import com.bsoftwares.myweather.R
import com.bsoftwares.myweather.databinding.FragmentFirstBinding
import com.bsoftwares.myweather.network.Network
import com.bsoftwares.myweather.utils.API_KEY
import com.bsoftwares.myweather.utils.UNIT_SYSTEM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Flow


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: FirstFragmentViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            FirstFragmentViewModel.Factory(activity.application)
        ).get(FirstFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            viewModel.getWeatherFlow(binding.etCity.text.toString())
        }

        viewModel.dataStateLD.observe(viewLifecycleOwner, { state ->
            when(state){
                is ApiState.Success -> {
                    viewModel.apiSleep()
                    findNavController().navigate(
                        R.id.action_FirstFragment_to_SecondFragment,
                        bundleOf("days" to state.data)
                    )
                }
                is ApiState.Error -> binding.tietCity.error = getString(R.string.existing_city)
            }
        })

        binding.etCity.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tietCity.error = null
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    suspend fun loadData(city: String) {
        withContext(Dispatchers.IO) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}