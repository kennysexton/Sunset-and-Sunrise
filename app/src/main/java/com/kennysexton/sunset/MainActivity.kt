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
import com.kennysexton.sunset.model.WeatherResponse
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

                var showBackButton by rememberSaveable { mutableStateOf(false) }
                var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

                var selectedCity by rememberSaveable { mutableStateOf<WeatherResponse?>(null) }

                // TODO: move this out of the way ( broken. I think it makes sense to move details to a full screen
                // navController doesn't fire on update. Instead we have to attach this listener
                navController.addOnDestinationChangedListener { _, destination, arguments ->
                    // Handle destination changes here
                    showBackButton = when (destination.route?.let { trimQueryParameters(it) }) {
                        WeatherLanding::class.java.name -> {
                            // show back button if we currently are in the details view
                            selectedCity != null
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
                            // Details screen is not a full navigation
                            onBackButtonClicked = {
                                if (selectedCity != null) selectedCity =
                                    null else navController.navigateUp()
                            },
                            onSettingsButtonClicked = { showSettingsDialog = true })
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = WeatherLanding(searchQuery = null),
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<WeatherLanding> {
                            WeatherLandingUI(
                                onAddLocationClicked = { navController.navigate(LocationSearch) },
                                selectedCity = selectedCity,
                                onRowClicked = { selectedCity = it }
                            )
                        }
                        composable<LocationSearch> {
                            LocationSearchUI(
                                onSearch = { navController.navigate(WeatherLanding(searchQuery = it)) }
                            )
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