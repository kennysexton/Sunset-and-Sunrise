package com.kennysexton.sunset.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.kennysexton.sunset.model.Units

@Composable
fun SettingsDialog(onDismiss: () -> Unit) {

    val vm = hiltViewModel<SettingsVM>()
    val currentUnits by vm.currentUnits.collectAsState()

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "Current: $currentUnits")
                Row() {
                    RadioButton(selected = currentUnits == Units.FAHRENHEIT, onClick = { vm.setUnits(Units.FAHRENHEIT) })
                    Text(text = "Fahrenheit")
                    RadioButton(selected = currentUnits == Units.CELSIUS, onClick = { vm.setUnits(Units.CELSIUS) })
                    Text(text = "Celsius")
                }
            }
        }
    }
}