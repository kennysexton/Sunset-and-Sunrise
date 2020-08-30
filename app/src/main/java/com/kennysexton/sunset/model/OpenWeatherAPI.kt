package com.kennysexton.sunset.model

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface OpenWeatherAPI {

    @GET("volley_array.json")
    fun getMovies() : Call<List<Movie>>

    companion object {

        private var BASE_URL = "http://velmm.com/apis/"

        fun create() : OpenWeatherAPI {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(OpenWeatherAPI::class.java)

        }
    }
}