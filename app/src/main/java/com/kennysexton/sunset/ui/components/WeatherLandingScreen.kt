package com.kennysexton.sunset.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kennysexton.sunset.WeatherLandingVM
import com.kennysexton.sunset.model.Coord
import com.kennysexton.sunset.model.Main
import com.kennysexton.sunset.model.Weather
import com.kennysexton.sunset.model.WeatherResponse
import com.kennysexton.sunset.ui.theme.SunriseSunsetTheme
import kotlin.math.roundToInt

@Composable
fun WeatherLandingScreen(
    innerPadding: PaddingValues,
    weatherList: List<WeatherResponse>,
    onAddLocationClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    )
    {
        LazyColumn() {
            items(weatherList) { location ->
                WeatherListItem(
                    weather = location,
                )
            }
        }

        BlueButton(
            onClick = onAddLocationClicked,
            modifier = Modifier
                .padding(innerPadding)
                .align(Alignment.BottomEnd)
        ) {
            Text(text = "Add Location")
        }


    }
}

@Composable
fun WeatherListItem(weather: WeatherResponse, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(18.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = weather.name,
            modifier = modifier
        )
        Text(
            text = "${weather.main.temp.roundToInt()}\u00B0",
            modifier = modifier
        )
        Box(
        )
        {
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${weather.weather.first().icon}@2x.png",
                contentDescription = weather.weather.first().description,
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun WeatherListItemPreview() {
    val sampleWeatherItem =
        WeatherResponse(
            coord = Coord("39.9523", "-75.1638"),
            name = "Philadelphia",
            main = Main(
                temp = 79.03f,
                feels_like = 79.03f,
                temp_min = 76.62f,
                temp_max = 81.27f,
                pressure = 1028.0f,
                humidity = 40.0f
            ),
            weather = listOf(Weather(description = "broken clouds", icon = "04d"))
        )

    SunriseSunsetTheme {
        WeatherListItem(sampleWeatherItem)
    }
}