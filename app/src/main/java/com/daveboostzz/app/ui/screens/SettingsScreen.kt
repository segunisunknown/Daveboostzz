package com.daveboostzz.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import com.daveboostzz.app.ui.components.ScreenScaffold

@Composable
fun SettingsScreen(onStartOverlay: () -> Unit, onStopOverlay: () -> Unit) {
    ScreenScaffold("SETTINGS", "Overlay controls and app information") {
        Button(onClick = onStartOverlay, modifier = Modifier.fillMaxWidth()) {
            Text("ENABLE FPS OVERLAY")
        }
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = onStopOverlay, modifier = Modifier.fillMaxWidth()) {
            Text("STOP FPS OVERLAY")
        }
        Spacer(Modifier.height(16.dp))
        Text("DAVEBOOSTZZ 1.0.0")
        Text("FPS overlay currently displays N/A; actual game FPS measurement is not implemented.")
    }
}
