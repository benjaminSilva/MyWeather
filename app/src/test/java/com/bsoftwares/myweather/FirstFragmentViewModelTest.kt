package com.bsoftwares.myweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bsoftwares.myweather.model.ApiState
import com.bsoftwares.myweather.model.City
import com.bsoftwares.myweather.model.Data
import com.bsoftwares.myweather.ui.first.FirstFragmentViewModel
import com.bsoftwares.myweather.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FirstFragmentViewModelTest {

    private lateinit var viewModel: FirstFragmentViewModel

    @get:Rule
    var hiltRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test() = runBlockingTest {

        viewModel = FirstFragmentViewModel(FakeRepository())
        viewModel.getWeatherFlow("Boston")
        assertThat(viewModel.dataStateLD.getOrAwaitValue()).isEqualTo(
            ApiState.Success(
                Data(
                    listOf(),
                    City("Boston")
                )
            )
        )
    }

}