package com.kennysexton.sunset.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.kennysexton.sunset.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBar(
    showBackButton: Boolean,
    onBackButtonClicked: () -> Unit,
    onSettingsButtonClicked: () -> Unit
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(stringResource(R.string.app_name))
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { onBackButtonClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },

        actions = {
            IconButton(onClick = { onSettingsButtonClicked() }) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings"
                )
            }
        },
    )
}