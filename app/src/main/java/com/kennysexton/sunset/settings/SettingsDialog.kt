package com.kennysexton.sunset.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.kennysexton.sunset.model.Units
import com.kennysexton.sunset.toTitleCase
import com.kennysexton.sunset.ui.components.CombinedRadioButton

@Composable
fun SettingsDialog(onDismiss: () -> Unit) {

    val vm = hiltViewModel<SettingsVM>()
    val currentUnits by vm.currentUnits.collectAsState()

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "Current: ${toTitleCase(currentUnits.name)}", modifier = Modifier.padding(bottom = 4.dp))
                Row() {
                    CombinedRadioButton(text = toTitleCase(Units.FAHRENHEIT.name), onClick = { vm.setUnits(Units.FAHRENHEIT) }, isSelected = currentUnits == Units.FAHRENHEIT)
                    CombinedRadioButton(
                        text = toTitleCase(Units.CELSIUS.name),
                        onClick = { vm.setUnits(Units.CELSIUS) },
                        isSelected = currentUnits == Units.CELSIUS
                    )
                }
            }
        }
    }
}