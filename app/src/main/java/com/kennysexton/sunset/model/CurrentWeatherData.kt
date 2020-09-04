package com.kennysexton.sunset.model


data class WeatherResponse(var coord: Coord, var name: String, var weather: List<Weather>)  //var weather: String, var main: String

data class Coord(var lat: String, var lon: String)

data class  Weather(var main: String, var description: String, var icon: String)