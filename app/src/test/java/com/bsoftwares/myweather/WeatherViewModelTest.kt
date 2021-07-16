package com.bsoftwares.myweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bsoftwares.myweather.model.ApiState
import com.bsoftwares.myweather.ui.first.WeatherViewModel
import com.bsoftwares.myweather.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    private lateinit var viewModel: WeatherViewModel

    @get:Rule
    var hiltRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = WeatherViewModel(FakeRepository())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test() {
        viewModel.getWeatherFlow("Boston")
        val days = viewModel.dataStateLD.getOrAwaitValue()

        if (days is ApiState.Success) {
            assertThat(days.data[0].city).isEqualTo("Boston")
        }

    }

    @ExperimentalCoroutinesApi
    @Test
    fun test2() {
        viewModel.apiSleep()
        assertThat(viewModel.dataStateLD.getOrAwaitValue()).isEqualTo(ApiState.Sleep)
    }
}