package com.kennysexton.sunset.weatherlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennysexton.sunset.BuildConfig
import com.kennysexton.sunset.Constants.UNITS_KEY
import com.kennysexton.sunset.di.DataStoreManager
import com.kennysexton.sunset.model.OpenWeatherInterface
import com.kennysexton.sunset.model.Units
import com.kennysexton.sunset.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherLandingVM @Inject constructor(
    private val dataStore: DataStoreManager,
    private val apiService: OpenWeatherInterface
) : ViewModel() {

    private val _weatherResponseList = MutableStateFlow<List<WeatherResponse>>(emptyList())
    val weatherResponseList: StateFlow<List<WeatherResponse>> = _weatherResponseList.asStateFlow()

    init {
        getWeatherData()
    }

    private suspend fun getUnits(): Units {
        var openWeatherUnits: Units = Units.FAHRENHEIT // default
        // Get Units
        dataStore.readStringDataStore(UNITS_KEY).first().let { units ->
            if (units != null) {
                println("units: $units")
                openWeatherUnits = Units.valueOf(units)
            }
        }
        return openWeatherUnits
    }

    private fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            val units = getUnits()

            // Make API call
            val response = apiService.getCurrentWeather(
                BuildConfig.OPEN_WEATHER_KEY,
                units.value
            )

            try {
                if (response.isSuccessful) {
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