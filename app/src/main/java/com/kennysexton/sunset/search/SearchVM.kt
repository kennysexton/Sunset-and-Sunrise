package com.kennysexton.sunset.search

import android.content.Context
import androidx.lifecycle.ViewModel
import com.kennysexton.sunset.weatherlist.WeatherLandingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchVM @Inject constructor(
    private val applicationContext: Context
) : ViewModel() {

//    private val _uiState = MutableStateFlow(SearchUiState())
//    val uiState: StateFlow<SearchUiState> = _uiState


//    fun onSearch(): (String) -> Unit {
//        return { text -> _uiState.update { it.copy(searchQuery = text) } }
//    }
}