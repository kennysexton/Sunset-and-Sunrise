package com.kennysexton.sunset.weatherDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kennysexton.sunset.model.WeatherResponse

@Composable
fun WeatherDetailsUI(weather: WeatherResponse?) {

    Column {
        Text(weather?.main?.temp.toString())
        Text(weather?.main?.humidity.toString())
        Text(weather?.main?.pressure.toString())
        Text(weather?.main?.feels_like.toString())
        Text(weather?.main?.temp_max.toString())
        Text(weather?.main?.temp_min.toString())
        Text(weather?.coord?.lat.toString())
        Text(weather?.coord?.lon.toString())
    }

}