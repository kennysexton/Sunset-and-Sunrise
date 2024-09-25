package com.kennysexton.sunset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kennysexton.sunset.navigation.LocationSearch
import com.kennysexton.sunset.navigation.WeatherDetails
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

                var showBackButton by rememberSaveable { mutableStateOf(false) }
                var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

                navController.currentDestination?.route

                // navController doesn't fire on update. Instead we have to attach this listener
                navController.addOnDestinationChangedListener { _, destination, arguments ->
                    // Handle destination changes here
                    showBackButton = when (destination.route) {
                        WeatherLanding::class.java.name -> {
                            false
                        }
                        else -> {
                            true
                        }
                    }
                }

                Scaffold(
                    topBar = {
                        TitleBar(
                            showBackButton = showBackButton,
                            onBackButtonClicked = { navController.navigateUp() },
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
                                onAddLocationClicked = { navController.navigate(LocationSearch) })
                        }
                        composable<LocationSearch> {
                            LocationSearchUI()
                        }
                    }
                }

                if (showSettingsDialog) {
                    SettingsDialog(onDismiss = { showSettingsDialog = false })
                }
            }
        }
    }
}