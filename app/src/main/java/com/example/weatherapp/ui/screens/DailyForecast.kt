package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun DailyForecast() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.LightGray)
//                .padding(25.dp)
//        ) {
//            Text("Halifax, Nova Scotia")
//        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cloud_icon),
                contentDescription = "Overcast Cloud"
            )
            Text("Sat, Sep 27")
            Text("High: 10°C  Low: 5°C. Overcast. Maximum winds 26kph. Humidity 76%")
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(15.dp)
            ){}
            Image(
                painter = painterResource(id = R.drawable.sun_icon),
                contentDescription = "Sunny"
            )
            Text("Sun, Sep 28")
            Text("High: 15°C  Low: 8°C. Sunny. Maximum winds 15kph. Humidity 60%")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ){}
            Image(
                painter = painterResource(id = R.drawable.wind_icon),
                contentDescription = "Windy"
            )
            Text("Mon, Sep 29")
            Text("High: 6°C  Low: 2°C. Windy. Maximum winds 27kph. Humidity 20%")
        }
    }
}
