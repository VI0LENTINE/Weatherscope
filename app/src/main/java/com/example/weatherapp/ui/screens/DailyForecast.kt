package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.MainViewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


@Composable
fun DailyForecast(mainViewModel : MainViewModel) {
    // Get the weather object from stateflow
    val weather by mainViewModel.weather.collectAsState()
    val forecastDays = weather?.forecast?.forecastDay

    // DailyForecast variables
    if (!forecastDays.isNullOrEmpty()) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            // List each forecast day in 3-day forecast
            items(forecastDays) { forecastDay ->
                val day = forecastDay.day
                val date = forecastDay.date

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    // Date
                    // FontWeight resource: https://developer.android.com/develop/ui/compose/text/style-text
                    Text(
                        text = date,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    // Icon
                    // String interpolation resource: https://kotlinlang.org/docs/basic-syntax.html#string-templates
                    Image(
                        painter = rememberAsyncImagePainter("https:${day.condition.icon}"),
                        contentDescription = "Weather Icon",
                        modifier = Modifier.size(128.dp)
                    )
                    // Temperature High & Low
                    Text(
                        text = "${day.maxTemp}°C High  " +
                                "${day.minTemp}°C Low",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    // Condition, Precipitation Chance & Amount, Wind Speed & Humidity
                    Text(
                        text = "${day.condition.text}. " +
                            "Chance of rain ${day.rainChance}%. " +
                            "Amount ${day.precipitationAmount}mm. " +
                            "Maximum winds ${day.maxWind}kph. " +
                            "Humidity ${day.avgHumidity}%.",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
