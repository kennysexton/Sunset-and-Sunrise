package com.kennysexton.sunset.model

data class WeatherResponse(
    val coord: Coordinate,
    val name: String,
    val weather: List<Weather>,
    val main: Main,
    val timezone: Int,
    val sys: Sys,
    val dt: Long // Current date and time
)

data class Coordinate(
    val lat: String,
    val lon: String
)

data class Weather(
    val description: String,
    val icon: String
)

data class Main(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Float,
    val humidity: Float
)

data class Sys(
    val country: String,
    val sunrise: Long,
    val sunset: Long
)