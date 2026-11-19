package com.daveboostzz.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daveboostzz.app.core.device.DeviceStatsRepository
import com.daveboostzz.app.ui.components.*
import com.daveboostzz.app.ui.theme.*

@Composable
fun DashboardScreen(
    repository: DeviceStatsRepository,
    onNavigate: (String) -> Unit,
    onStartOverlay: () -> Unit
) {
    val stats by repository.snapshot.collectAsState()
    ScreenScaffold("DAVEBOOSTZZ", "Gaming utility dashboard") {
        CyberCard(Modifier.fillMaxWidth()) {
            Text("DEVICE STATUS", color = NeonRed, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            Text("CPU: ${stats.cpuLoad?.let { "${(it * 100).toInt()}%" } ?: "N/A"}", color = TextWhite)
            Text("RAM used: ${stats.usedRamMb?.let { "$it MB" } ?: "N/A"}", color = TextWhite)
            Text("Battery: ${stats.batteryPercent}%", color = TextWhite)
            Text("Temperature: ${stats.batteryTempC?.let { "$it °C" } ?: "N/A"}", color = MutedGrey)
        }
        Spacer(Modifier.height(14.dp))
        NeonButton("ENABLE FPS OVERLAY", onStartOverlay, Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        listOf("Performance", "Games", "Battery", "Shizuku", "Network", "Background Apps", "Notifications", "Statistics").forEach { destination ->
            OutlinedButton(onClick = { onNavigate(destination) }, modifier = Modifier.fillMaxWidth()) {
                Text(destination)
            }
            Spacer(Modifier.height(5.dp))
        }
        Text("Note: the overlay currently shows FPS: N/A; it does not measure game-render FPS.", color = MutedGrey, style = MaterialTheme.typography.bodySmall)
    }
}
