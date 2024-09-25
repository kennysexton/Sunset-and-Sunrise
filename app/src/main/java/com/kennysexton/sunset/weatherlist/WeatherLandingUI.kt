package com.kennysexton.sunset.weatherlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kennysexton.sunset.model.Coordinate
import com.kennysexton.sunset.model.Main
import com.kennysexton.sunset.model.Sys
import com.kennysexton.sunset.model.Weather
import com.kennysexton.sunset.model.WeatherResponse
import com.kennysexton.sunset.ui.components.BlueButton
import com.kennysexton.sunset.ui.theme.SunriseSunsetTheme
import com.kennysexton.sunset.weatherDetails.WeatherDetailsUI
import kotlin.math.roundToInt

@Composable
fun WeatherLandingUI(
    onAddLocationClicked: () -> Unit
) {
    val weatherVM = hiltViewModel<WeatherLandingVM>()

    val uiState by weatherVM.uiState.collectAsState()
    var selectedCity by rememberSaveable { mutableStateOf<WeatherResponse?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        //TODO: add backstack functionality
        if (selectedCity != null) {
            WeatherDetailsUI(weather = selectedCity)
        } else {
            LazyColumn {
                items(uiState.weatherList) { location ->
                    WeatherListItem(
                        weather = location,
                        onRowClicked = { selectedCity = it }
                    )
                }
            }
        }

        BlueButton(
            onClick = onAddLocationClicked,
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Text(text = "Add Location")
        }

    }
}

@Composable
fun WeatherListItem(
    weather: WeatherResponse,
    modifier: Modifier = Modifier,
    onRowClicked: (WeatherResponse) -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(18.dp)
            .fillMaxWidth()
            .clickable {
                onRowClicked(weather)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = weather.name,
            modifier = modifier
        )
        //TODO, add unit symbol
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
            coord = Coordinate("39.9523", "-75.1638"),
            name = "Philadelphia",
            main = Main(
                temp = 79.03f,
                feels_like = 79.03f,
                temp_min = 76.62f,
                temp_max = 81.27f,
                pressure = 1028.0f,
                humidity = 40.0f
            ),
            dt = 1234,
            timezone = -18000,
            sys = Sys(country = "US", sunrise = 1234, sunset = 1234),
            weather = listOf(Weather(description = "broken clouds", icon = "04d"))
        )

    SunriseSunsetTheme {
        WeatherListItem(sampleWeatherItem)
    }
}