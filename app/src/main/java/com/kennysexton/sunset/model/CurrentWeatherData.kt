package com.kennysexton.sunset.model


data class WeatherResponse(var coord: Coord, var name: String)  //var weather: String, var main: String

data class Coord(var lat: String, var lon: String)