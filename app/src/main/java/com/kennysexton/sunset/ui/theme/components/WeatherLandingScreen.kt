package com.kennysexton.sunset.ui.theme.components

import com.kennysexton.sunset.model.Coord
import com.kennysexton.sunset.model.Main
import com.kennysexton.sunset.model.Weather
import com.kennysexton.sunset.model.WeatherResponse
import com.kennysexton.sunset.ui.theme.SunriseSunsetTheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WeatherLandingScreen(innerPadding: PaddingValues, weatherList: List<WeatherResponse>) {
    LazyColumn {
        items(weatherList) { location ->
            WeatherListItem(
                weather = location,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun WeatherListItem(weather: WeatherResponse, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ${weather.name}!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun WeatherListItemPreview() {

    val sampleWeatherList = listOf(
        WeatherResponse(
            coord = Coord("0", "0"),
            name = "Test Location",
            main = Main(
                temp = 0.0f,
                feels_like = 0.0f,
                temp_min = 0.0f,
                temp_max = 0.0f,
                pressure = 0.0f,
                humidity = 0.0f
            ),
            weather = listOf(Weather(description = "Test", icon = "Test"))
        )
    )
    SunriseSunsetTheme {
        WeatherLandingScreen(innerPadding = PaddingValues(), sampleWeatherList)
    }
}