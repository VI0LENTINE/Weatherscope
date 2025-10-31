package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.CurrentWeather
import com.example.weatherapp.ui.screens.DailyForecast
import com.example.weatherapp.ui.theme.WeatherAppTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlin.getValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.isGranted
import android.Manifest
import com.google.android.gms.location.Priority
import com.google.android.gms.location.LocationServices
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import com.google.android.gms.tasks.CancellationTokenSource
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    // Store view model in MainActivity
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                GetLocation()
                DisplayUI(mainViewModel)
            }
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun GetLocation() {
        // Remember the permission state(asking for Fine location)
        val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionState.status.isGranted) {
            Log.i("TESTING", "Hurray, permission granted!")

            // Get Location
            val currentContext = LocalContext.current
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(currentContext)

            if (ContextCompat.checkSelfPermission(
                    currentContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
            {
                val cancellationTokenSource = CancellationTokenSource()

                Log.i("TESTING", "Requesting location...")

                fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            val lat = location.latitude.toString()
                            val lng = location.longitude.toString()

                            Log.i("TESTING", "Success: $lat $lng")

                            val coordinates = "$lat,$lng"

                            // call a function, like in View Model GetWeather(), to do something with location...
                            mainViewModel.getWeather(coordinates)
                        }
                        else {
                            Log.i("TESTING", "Problem encountered: Location returned null")
                        }
                    }
            }
        }
        else {
            // Run a side-effect (coroutine) to get permission. The permission popup.
            LaunchedEffect(permissionState){
                permissionState.launchPermissionRequest()
            }
        }
    }
}

@Composable
fun PhoneLocalTimeText() {
    val currentTime = remember { mutableStateOf(System.currentTimeMillis()) }

    // Update every minute
    // Resources: https://developer.android.com/develop/ui/compose/side-effects#launchedeffect
    //            https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/delay.html
    LaunchedEffect(Unit) {
        while (true) {
            currentTime.value = System.currentTimeMillis()
            kotlinx.coroutines.delay(60_000L) // 1 minute
        }
    }

    // Resources: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
    val sdf = SimpleDateFormat("h:mm a", Locale.getDefault())
    val timeString = sdf.format(Date(currentTime.value))

    Text(
        text = "Local time: $timeString",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    val weather by mainViewModel.weather.collectAsState()
    val location = weather?.location

    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.foundation.layout.Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${location?.locationName ?: "Loading..."}, ${location?.locationRegion ?: ""}",
                                style = MaterialTheme.typography.titleLarge
                            )
                            PhoneLocalTimeText()
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
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
        NavHost(
            navController = navController,
            startDestination = "current",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = "current") {
                CurrentWeather(mainViewModel)
            }

            composable(route = "forecast") {
                DailyForecast(mainViewModel)
            }
        }
    }
}
