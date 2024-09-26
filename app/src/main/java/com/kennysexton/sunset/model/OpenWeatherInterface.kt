package com.kennysexton.sunset.model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherInterface {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("appid") api_key: String,
        @Query("q") location: String,
        @Query("units") units: String
    ): Response<WeatherResponse>

}