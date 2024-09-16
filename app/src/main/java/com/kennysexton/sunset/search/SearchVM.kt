package com.kennysexton.sunset.search

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchVM @Inject constructor(
    private val applicationContext: Context
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()


    fun onSearch(): (String) -> Unit {
        return { it -> _searchQuery.value = it }
    }

    fun onQueryChange(): (String) -> Unit {
        return { it -> _searchQuery.value = it}
    }

}