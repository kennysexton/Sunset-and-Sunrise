package com.kennysexton.sunset.model

data class WeatherResponse(
    var coord: Coord,
    var name: String,
    var weather: List<Weather>,
    var main: Main
)

data class Coord(
    var lat: String,
    var lon: String
)

data class Weather(
    var description: String,
    var icon: String
)

data class Main(
    var temp: Float,
    var feels_like: Float,
    var temp_min: Float,
    var temp_max: Float,
    var pressure: Float,
    var humidity: Float
)