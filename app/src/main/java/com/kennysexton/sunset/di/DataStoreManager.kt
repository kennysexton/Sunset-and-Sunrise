package com.kennysexton.sunset.di

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import com.kennysexton.sunset.Constants.SETTINGS_DATA_STORE
import com.kennysexton.sunset.model.Units
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreManager @Inject constructor(@ApplicationContext private val appContext: Context) {

    private val dataStore = PreferenceDataStoreFactory.create(
        produceFile = { appContext.preferencesDataStoreFile(SETTINGS_DATA_STORE) })

    suspend fun writeStringDataStore(unitsValue: Units, preferenceKey: String) {
        val dataStoreKey = stringPreferencesKey(preferenceKey)
        dataStore.edit { settings ->
            settings[dataStoreKey] = unitsValue.name
        }
    }

    fun readStringDataStore(preferenceKey: String): Flow<String?> {
        return dataStore.data.map { preferences -> preferences[stringPreferencesKey(preferenceKey)] }
    }

}