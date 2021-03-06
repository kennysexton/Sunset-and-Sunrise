package com.kennysexton.sunset.model

import com.kennysexton.sunset.BuildConfig
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {

    @GET("weather?q=Philadelphia")
    fun getCurrentWeather(@Query("appid") api_key: String, @Query("units") units: String): Call<WeatherResponse>

    companion object {

        private var BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun create() : OpenWeatherAPI {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(OpenWeatherAPI::class.java)

        }
    }
}