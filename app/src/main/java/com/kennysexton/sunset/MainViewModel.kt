package com.kennysexton.sunset

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennysexton.sunset.model.OpenWeatherInterface
import com.kennysexton.sunset.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val weatherResponseList: ArrayList<WeatherResponse> = ArrayList()

    fun getWeatherData(): List<WeatherResponse> {

        viewModelScope.launch(Dispatchers.IO) {
            val response = OpenWeatherInterface.OpenWeatherAPI.create().getCurrentWeather(
                BuildConfig.OPEN_WEATHER_KEY,
                "imperial"
            )

            try {
                if (response.isSuccessful) {
                    Log.d("Networking", "response success: ${response.body()}")
                    response.body()?.let { weatherResponseList.add(it) }
                } else {
                    Log.e("Networking", "failed call to OpenWeatherAPI")
                }
            } catch (e: Exception) {
                Log.e("Networking", "failed call to OpenWeatherAPI: ${e.message}")
            }
        }
        return weatherResponseList
    }

}