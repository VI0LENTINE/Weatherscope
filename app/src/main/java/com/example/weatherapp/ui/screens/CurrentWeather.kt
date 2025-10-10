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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.MainViewModel
import coil.compose.rememberAsyncImagePainter

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
            // CurrentWeather variables
            Image(
                painter = painterResource(id = current.imageId),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(128.dp)
            )

            Text(text = current?.condition.toString(),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = current?.temperature.toString(),
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = current?.precipitationType.toString(),
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = current?.precipitationAmount.toString(),
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = current?.wind.toString(),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ){
//        Row(
//            horizontalArrangement = Arrangement.SpaceAround,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.rain_cloud_icon),
//                contentDescription = "Raincloud"
//            )
//        }
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.SpaceAround,
//            modifier = Modifier
//                .fillMaxWidth()
//        ){
//            Text("Rainy")
//            Text("6°C")
//            Text("Feels like 2°C")
//            Text("2mm of rain")
//            Text("Wind NW 18 kph")
//        }
//    }

//    Column(
//        verticalArrangement = Arrangement.Top,
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.LightGray)
//            .padding(25.dp)
//    ) {
//        Text("Halifax, Nova Scotia")
//    }