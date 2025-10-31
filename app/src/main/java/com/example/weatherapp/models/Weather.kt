package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

data class Location(
    @SerializedName("name") val locationName: String,
    @SerializedName("region") val locationRegion: String,
    @SerializedName("country") val locationCountry: String,
    @SerializedName("lat") val locationLat: Float,
    @SerializedName("lon") val locationLon: Float,
    // Extra feature: local time refresh
    @SerializedName("localtime") val locationLocalTime: String
)

// --- Current Weather Section ---
data class Current(
    // Temperature
    @SerializedName("temp_c") val temperature: Float,
    // Precipitation amount
    @SerializedName("precip_mm") val precipitationAmount: Float,
    // Wind speed & direction
    @SerializedName("wind_kph") val windSpeed: Float,
    @SerializedName("wind_dir") val windDirection: String,
    // Condition
    val condition: Condition,
)

data class Condition(
    val text: String,
    val icon: String
)

// --- Forecast Section ---
data class Forecast(
    @SerializedName("forecastday") val forecastDay: List<ForecastDay>
)

data class ForecastDay(
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: Day
)

data class Day(
    // Max/min temps
    @SerializedName("maxtemp_c") val maxTemp: Float,
    @SerializedName("mintemp_c") val minTemp: Float,
    // Precipitation
    @SerializedName("totalprecip_mm") val precipitationAmount: Float,
    @SerializedName("daily_chance_of_rain") val rainChance: String,
    //Wind
    @SerializedName("maxwind_kph") val maxWind: Float,
    //Humidity
    @SerializedName("avghumidity") val avgHumidity: Float,
    //Condition
    val condition: Condition
)