package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.weatherapp.MainViewModel

@Composable
fun DailyForecast(mainViewModel : MainViewModel) {
    // Get the weather object from stateflow
    val weather by mainViewModel.weather.collectAsState()

    // DailyForecast variables
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ){
        // List each forecast day in 3-day forecast
        for(item in weather?.forecast!!){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = item.date,
                    style = MaterialTheme.typography.titleSmall
                )

                Image(
                    painter = painterResource(id = item.imageId),
                    contentDescription = "Forecast Icon",
                    modifier = Modifier.size(40.dp)
                )

                Text(
                    text = item.temperatureHigh,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = item.temperatureLow,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = item.condition,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = item.precipitationType,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = item.precipitationAmount,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = item.precipitationProbability,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = item.wind,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = item.humidity,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

//Column(
//modifier = Modifier
//.fillMaxSize()
//.background(Color.White),
//verticalArrangement = Arrangement.Top,
//horizontalAlignment = Alignment.CenterHorizontally
//) {

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.LightGray)
//                .padding(25.dp)
//        ) {
//            Text("Halifax, Nova Scotia")
//        }

//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(4.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.cloud_icon),
//            contentDescription = "Overcast Cloud"
//        )
//        Text("Sat, Sep 27")
//        Text("High: 10°C  Low: 5°C. Overcast. Maximum winds 26kph. Humidity 76%")
//    }
//
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(4.dp)
//    ) {
//        Row (
//            modifier = Modifier.fillMaxWidth()
//                .padding(15.dp)
//        ){}
//        Image(
//            painter = painterResource(id = R.drawable.sun_icon),
//            contentDescription = "Sunny"
//        )
//        Text("Sun, Sep 28")
//        Text("High: 15°C  Low: 8°C. Sunny. Maximum winds 15kph. Humidity 60%")
//    }
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(4.dp)
//    ) {
//        Row (
//            modifier = Modifier.fillMaxWidth()
//                .padding(16.dp)
//        ){}
//        Image(
//            painter = painterResource(id = R.drawable.wind_icon),
//            contentDescription = "Windy"
//        )
//        Text("Mon, Sep 29")
//        Text("High: 6°C  Low: 2°C. Windy. Maximum winds 27kph. Humidity 20%")
//    }
//}

