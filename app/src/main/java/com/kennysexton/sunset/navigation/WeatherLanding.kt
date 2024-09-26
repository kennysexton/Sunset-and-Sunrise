package com.kennysexton.sunset.navigation

import kotlinx.serialization.Serializable

@Serializable
data class WeatherLanding(
    val searchQuery: String? = null,
)