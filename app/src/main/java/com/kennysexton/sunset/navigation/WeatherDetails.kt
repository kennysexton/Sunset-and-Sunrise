package com.kennysexton.sunset.navigation

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetails(
    val locationName: String? = null,
)
