package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.Weather
import com.example.weatherapp.services.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class MainViewModel : ViewModel() {
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather.asStateFlow()

    // Retrofit instance
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Initialize interface surface
    val weatherService: WeatherService = retrofit.create(WeatherService::class.java)

    private val apiKey = "230c44f3f3d548c4a02153008251710"

    fun getWeather(city: String = "Halifax") {
        viewModelScope.launch {
            try {
                val response = weatherService.getWeather(apiKey, city, 14, "no", "no")
                _weather.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
