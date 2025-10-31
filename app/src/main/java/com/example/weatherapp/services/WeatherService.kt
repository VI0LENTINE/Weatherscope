package com.example.weatherapp.services

import com.example.weatherapp.models.Weather
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    // Make the API call
    @GET("v1/forecast.json")
    suspend fun getWeather(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): Weather
}
