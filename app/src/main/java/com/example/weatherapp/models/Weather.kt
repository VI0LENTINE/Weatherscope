package com.example.weatherapp.models

data class Weather(
    //a single Current model:
    val current: Current,
    // and a List<Forecast> model.
    val forecast: List<Forecast>
)

data class Current(
//    weather image
    val imageId: Int,
//    condition
    val condition: String,
//    temperature
    val temperature: String,
//    precipitation type and amount
    val precipitationType: String,
    val precipitationAmount: String,
//    wind direction and speed
    val wind: String,
)

data class Forecast(
// date
    val date: String,
// weather image
    val imageId: Int,
// temperature high and low
    val temperatureHigh: String,
    val temperatureLow: String,
// condition
    val condition: String,
// precipitation type, amount, and probability
    val precipitationType: String,
    val precipitationAmount: String,
    val precipitationProbability: String,
// wind direction and speed
    val wind: String,
// humidity
    val humidity: String

)
