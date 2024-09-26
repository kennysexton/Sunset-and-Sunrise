package com.kennysexton.sunset.weatherlist

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.kennysexton.sunset.BuildConfig
import com.kennysexton.sunset.Constants.UNITS_KEY
import com.kennysexton.sunset.di.DataStoreManager
import com.kennysexton.sunset.model.OpenWeatherInterface
import com.kennysexton.sunset.model.Units
import com.kennysexton.sunset.navigation.WeatherLanding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherLandingVM @Inject constructor(
    private val dataStore: DataStoreManager,
    private val apiService: OpenWeatherInterface,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val searchQuery = savedStateHandle.toRoute<WeatherLanding>().searchQuery

    private val _uiState = MutableStateFlow(WeatherLandingUiState())
    val uiState: StateFlow<WeatherLandingUiState> = _uiState

    init {

        // If searching, go to details. Else show all options in list
        if (searchQuery != null) {
            getWeatherData(location = searchQuery)
        } else {
            //TODO get saved locations from storage
            // Then get updated weather for each
//            getWeatherData()
        }
    }

    // TODO: consolidate read and write into one function call
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

    private fun getWeatherData(location: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val units = getUnits()

            // Make API call
            val response = apiService.getCurrentWeather(
                BuildConfig.OPEN_WEATHER_KEY,
                location,
                units.value
            )

            try {
                if (response.isSuccessful) {
                    response.body()
                        ?.let { weatherResponse ->
                            _uiState.update {
                                it.copy(
                                    weatherList = listOf(
                                        weatherResponse
                                    )
                                )
                            }
                        }
                } else {
                    Log.e("Networking", "failed call to OpenWeatherAPI")
                }
            } catch (e: Exception) {
                Log.e("Networking", "failed call to OpenWeatherAPI: ${e.message}")
            }
        }
    }
}