package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.CurrentWeather
import com.example.weatherapp.ui.screens.DailyForecast
import com.example.weatherapp.ui.theme.WeatherAppTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
//              Greeting()
//              CurrentWeather()
//                DailyForecast()
                DisplayUI()
            }
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI() {
    val navController = rememberNavController()

    // Variable to store the selected value in Nav Bar
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ), title = {
                    Text("Halifax, Nova Scotia")
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                // Navbar items
                // Link to CurrentWeather screen
                NavigationBarItem(
                    label = {
                        Text("Now")
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_action_clock),
                            contentDescription = "Clock"
                        )
                    },
                    selected = selectedItem == 0,
                    onClick = {
                        selectedItem = 0
                        navController.navigate("current")
                    }
                )

                // Link to DailyForecast screen
                NavigationBarItem(
                    label = {
                        Text("Daily")
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_action_calendar),
                            contentDescription = "Calendar"
                        )
                    },
                    selected = selectedItem == 1,
                    onClick = {
                        selectedItem = 1
                        navController.navigate("forecast")
                    }
                )
            }
        }
    ) { innerPadding ->
        // Use a Nav Host to render the screens
        NavHost(
            navController = navController,
            startDestination = "current",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Display CurrentWeather screen
            composable(route = "current") {
                CurrentWeather()
            }
            // Display DailyForecast screen
            composable(route = "forecast") {
                DailyForecast()
            }
        }
    }
}

//@Composable
//fun Greeting() {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxSize()
//    ){
//        Row(
//            horizontalArrangement = Arrangement.SpaceAround,
//            modifier = Modifier
//                .fillMaxWidth()
//        )
//        {
//            Image(
//                painter = painterResource(id = R.drawable.android_logo),
//                contentDescription = "Android Logo")
//        }
//
//
//        Row(
//            horizontalArrangement = Arrangement.SpaceAround,
//            modifier = Modifier
//                .background(Color.LightGray)
//                .fillMaxWidth()
//        ) {
//            Text(
//                text = "Hello",
//            )
//            Text(
//                text = "Android",
//            )
//            Text(
//                text = "Welcome to my app",
//            )
//        }
//    }
//}
