package com.daveboostzz.app.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import com.daveboostzz.app.core.device.DeviceStatsRepository
import com.daveboostzz.app.ui.components.*
import com.daveboostzz.app.ui.theme.*

@Composable
fun BatteryScreen(repository: DeviceStatsRepository) {
    val stats by repository.snapshot.collectAsState()
    ScreenScaffold("BATTERY", "Battery information") {
        CyberCard(Modifier.fillMaxWidth()) {
            Text("Charge level: ${stats.batteryPercent}%", color = TextWhite)
            Text("Battery temperature: ${stats.batteryTempC?.let { "$it °C" } ?: "Unavailable"}", color = TextWhite)
        }
        Spacer(Modifier.height(12.dp))
        Text("Battery saver controls are not enabled in this starter build. Use Android system settings for power-saving options.", color = MutedGrey)
    }
}
