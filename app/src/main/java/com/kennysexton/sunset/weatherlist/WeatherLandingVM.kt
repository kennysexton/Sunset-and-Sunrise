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
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherLandingUiState())
    val uiState: StateFlow<WeatherLandingUiState> = _uiState

    init {

            //TODO get saved locations from storage
            // Then get updated weather for each
//            getWeatherData()
    }


}