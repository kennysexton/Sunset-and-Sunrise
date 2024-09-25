package com.kennysexton.sunset.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennysexton.sunset.Constants.UNITS_KEY
import com.kennysexton.sunset.di.DataStoreManager
import com.kennysexton.sunset.model.Units
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor(
    private val dataStore: DataStoreManager
) : ViewModel() {

    //TODO: MVI this baddie
    private val _currentUnits = MutableStateFlow(Units.FAHRENHEIT)
    val currentUnits = _currentUnits.asStateFlow()

    init {
        viewModelScope.launch {
            getUnits()
        }
    }

    fun setUnits(units: Units) {
        viewModelScope.launch {
            dataStore.writeStringDataStore(units, UNITS_KEY)
            // after our write. Let's check the data store again
            dataStore.readStringDataStore(UNITS_KEY).collect { units ->
                if (units != null) {
                    _currentUnits.value = Units.valueOf(units)
                }
            }
        }
    }

    private suspend fun getUnits() {
        dataStore.readStringDataStore(UNITS_KEY).collect { units ->
            if (units != null) {
                _currentUnits.value = Units.valueOf(units)
            }
        }
    }


}