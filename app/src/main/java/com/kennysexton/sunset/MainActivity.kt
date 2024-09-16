package com.kennysexton.sunset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kennysexton.sunset.navigation.LocationSearch
import com.kennysexton.sunset.navigation.WeatherLanding
import com.kennysexton.sunset.search.LocationSearchUI
import com.kennysexton.sunset.settings.SettingsDialog
import com.kennysexton.sunset.ui.components.TitleBar
import com.kennysexton.sunset.ui.theme.SunriseSunsetTheme
import com.kennysexton.sunset.weatherlist.WeatherLandingUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SunriseSunsetTheme {
                val navController = rememberNavController()

                var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

                Scaffold(
                    topBar = {
                        TitleBar(
                            navController,
                            onSettingsButtonClicked = { showSettingsDialog = true })
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = WeatherLanding,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<WeatherLanding> {
                            WeatherLandingUI(
                                innerPadding,
                                onAddLocationClicked = { navController.navigate(LocationSearch) })
                        }
                        composable<LocationSearch> {
                            LocationSearchUI(innerPadding)
                        }
                    }
                }

                if(showSettingsDialog) {
                    SettingsDialog(onDismiss = { showSettingsDialog = false })
                }
            }
        }
    }
}