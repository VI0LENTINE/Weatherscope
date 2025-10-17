package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.MainViewModel
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CurrentWeather(mainViewModel: MainViewModel) {

    // Get the weather object from stateflow
    val weather by mainViewModel.weather.collectAsState()
    val current = weather?.current

    if(current != null) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
        // CurrentWeather variables:
            // - Weather icon
            Image(
                painter = rememberAsyncImagePainter("https:${current.condition.icon}"),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(128.dp)
            )

            // - Condition
            Text(text = current.condition.text,
                style = MaterialTheme.typography.titleMedium,
            )

            // - Temperature
            // FontWeight resource: https://developer.android.com/develop/ui/compose/text/style-text
            // String interpolation resource: https://kotlinlang.org/docs/basic-syntax.html#string-templates
            Text(
                text = "${current.temperature}°C",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            // - Precipitation amount
            Text(
                text = "${current.precipitationAmount}mm",
                style = MaterialTheme.typography.titleSmall
            )

            // - Wind speed and direction
            Text(
                text = "Wind ${current.windDirection} ${current.windSpeed}kph",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}
