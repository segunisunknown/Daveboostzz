package com.daveboostzz.app.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import com.daveboostzz.app.core.device.DeviceStatsRepository
import com.daveboostzz.app.ui.components.ScreenScaffold
import com.daveboostzz.app.ui.theme.MutedGrey
import com.daveboostzz.app.ui.theme.TextWhite

@Composable
fun StatisticsScreen(repository: DeviceStatsRepository) {
    val stats by repository.snapshot.collectAsState()
    ScreenScaffold("STATISTICS", "Current device snapshot") {
        Text("CPU: ${stats.cpuLoad?.let { "${(it * 100).toInt()}%" } ?: "N/A"}", color = TextWhite)
        Text("RAM: ${stats.usedRamMb?.let { "$it MB" } ?: "N/A"}", color = TextWhite)
        Text("Battery: ${stats.batteryPercent}%", color = TextWhite)
        Text("This is a current snapshot, not historical analytics.", color = MutedGrey)
    }
}
