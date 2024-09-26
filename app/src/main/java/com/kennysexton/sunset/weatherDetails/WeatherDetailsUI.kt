package com.kennysexton.sunset.weatherDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kennysexton.sunset.model.WeatherResponse
import com.kennysexton.sunset.weatherlist.WeatherLandingVM


@Composable
fun WeatherDetailsUI() {

    val weatherVM = hiltViewModel<WeatherDetailsVM>()

    val uiState by weatherVM.uiState.collectAsState()

    Column {
        Text(uiState.weather?.main?.temp.toString())
        Text(uiState.weather?.main?.humidity.toString())
        Text(uiState.weather?.main?.pressure.toString())
        Text(uiState.weather?.main?.feels_like.toString())
        Text(uiState.weather?.main?.temp_max.toString())
        Text(uiState.weather?.main?.temp_min.toString())
        Text(uiState.weather?.coord?.lat.toString())
        Text(uiState.weather?.coord?.lon.toString())
    }
}