package com.kennysexton.sunset

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennysexton.sunset.model.OpenWeatherInterface
import com.kennysexton.sunset.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherLandingVM @Inject constructor(
    private val applicationContext: Context
) : ViewModel() {

    private val _weatherResponseList = MutableStateFlow<List<WeatherResponse>>(emptyList())
    val weatherResponseList: StateFlow<List<WeatherResponse>> = _weatherResponseList.asStateFlow()

    init {
        getWeatherData()
    }

    private fun getWeatherData() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = OpenWeatherInterface.OpenWeatherAPI.create().getCurrentWeather(
                BuildConfig.OPEN_WEATHER_KEY,
                "imperial"
            )

            try {
                if (response.isSuccessful) {
                    Log.d("Networking", "response success: ${response.body()}")
                    response.body()?.let { _weatherResponseList.value = listOf(it) }
                } else {
                    Log.e("Networking", "failed call to OpenWeatherAPI")
                }
            } catch (e: Exception) {
                Log.e("Networking", "failed call to OpenWeatherAPI: ${e.message}")
            }
        }

    }
}