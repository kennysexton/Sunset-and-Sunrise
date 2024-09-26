package com.kennysexton.sunset.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSearchUI(
    onSearch: (String) -> Unit
) {
    val vm = hiltViewModel<SearchVM>()

//    val uiState by vm.uiState.collectAsState()
    var searchQuery by rememberSaveable { mutableStateOf("") }


    Column() {
        Spacer(Modifier.weight(1f))

        SearchBar(
            query = searchQuery,
            onQueryChange = {
                searchQuery = it
            },
            onSearch = { onSearch(it) },
            active = true,
            onActiveChange = {},
            placeholder = { Text(text = "Enter a city") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    // TODO, use own theme color
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            },
        ) {

        }
    }
}