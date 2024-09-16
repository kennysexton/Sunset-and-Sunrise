package com.kennysexton.sunset.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.kennysexton.sunset.Constants.SETTINGS_DATA_STORE
import com.kennysexton.sunset.model.Units
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor(
    private val appContext: Context,
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
) : ViewModel() {

    private val _currentUnits = MutableStateFlow(Units.FAHRENHEIT)
    val currentUnits = _currentUnits.asStateFlow()

    init {
        getUnits()
    }

    fun setUnits(units: Units) {
        viewModelScope.launch {
            writeUnitsDataStore(units)

            getUnits()
        }
    }

    fun getUnits() {
        var dataStoreResult: String? = null
        viewModelScope.launch {
            dataStoreResult = readUnitsDataStore()

            if (dataStoreResult == null) {
                println("Datastore value not found")
            }
            // Set to value or Fahrenheit if null
            _currentUnits.value =
                (readUnitsDataStore()?.let { Units.valueOf(it) } ?: Units.FAHRENHEIT)
        }
    }

    suspend fun writeUnitsDataStore(units: Units) {
        val dataStoreKey = stringPreferencesKey(SETTINGS_DATA_STORE)
        dataStore.edit { settings ->
            settings[dataStoreKey] = units.name
        }
    }

    suspend fun readUnitsDataStore(): String? {
        val dataStoreKey = stringPreferencesKey(SETTINGS_DATA_STORE)
        return dataStore.data.first()[dataStoreKey]
    }

}