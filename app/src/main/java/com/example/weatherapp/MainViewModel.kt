package com.example.weatherapp

import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.Current
import com.example.weatherapp.models.Forecast
import com.example.weatherapp.models.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class MainViewModel : ViewModel() {
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather.asStateFlow()

    init {
        // Create Weather placeholder
        val weather = Weather(
            current = Current(
                imageId = R.drawable.rain_cloud_icon,
                condition = "Rainy",
                temperature = "6°C",
                precipitationType = "Rain",
                precipitationAmount = "2mm",
                wind = "Wind NW 18 kph"
            ),
            forecast = listOf(
                // Saturday
                Forecast(
                    date = "Saturday",
                    imageId = R.drawable.cloud_icon,
                    temperatureHigh = "High: 10°C",
                    temperatureLow = "Low: 5°C",
                    condition = "Overcast",
                    precipitationType = "None",
                    precipitationAmount = "0mm",
                    precipitationProbability = "10%",
                    wind = "Maximum winds 26kph.",
                    humidity = "Humidity 76%"
                ),
                // Sunday
                Forecast(
                    date = "Sunday",
                    imageId = R.drawable.sun_icon,
                    temperatureHigh = "High: 15°C",
                    temperatureLow = "Low: 8°C",
                    condition = "Sunny",
                    precipitationType = "None",
                    precipitationAmount = "0mm",
                    precipitationProbability = "0%",
                    wind = "Maximum winds 15kph",
                    humidity = "Humidity 60%"
                ),
                // Monday
                Forecast(
                    date = "Monday",
                    imageId = R.drawable.wind_icon,
                    temperatureHigh = "High: 6°C",
                    temperatureLow = "Low: 2°C",
                    condition = "Windy",
                    precipitationType = "None",
                    precipitationAmount = "0mm",
                    precipitationProbability = "5%",
                    wind = "Maximum winds 27kph",
                    humidity = "Humidity 20%"
                )
            )
        )

        _weather.value = weather
    }
}
