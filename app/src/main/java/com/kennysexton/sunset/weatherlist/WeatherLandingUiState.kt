package com.kennysexton.sunset.weatherlist

import com.kennysexton.sunset.model.WeatherResponse

data class WeatherLandingUiState(
    val weatherList: List<WeatherResponse> = emptyList()
)
