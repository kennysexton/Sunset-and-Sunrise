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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSearchUI() {
    val vm = hiltViewModel<SearchVM>()

    val searchQuery by vm.searchQuery.collectAsState()


    Column() {
        Spacer(Modifier.weight(1f))

        SearchBar(
            query = searchQuery,
            onQueryChange = vm.onQueryChange(),
            onSearch = vm.onSearch(),
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