package com.daveboostzz.app.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import com.daveboostzz.app.core.device.DeviceStatsRepository
import com.daveboostzz.app.ui.components.*
import com.daveboostzz.app.ui.theme.*

@Composable
fun PerformanceScreen(repository: DeviceStatsRepository) {
    val stats by repository.snapshot.collectAsState()
    ScreenScaffold("PERFORMANCE", "Live device readings") {
        CyberCard(Modifier.fillMaxWidth()) {
            Text("CPU load: ${stats.cpuLoad?.let { "${(it * 100).toInt()}%" } ?: "Unavailable"}", color = TextWhite)
            Spacer(Modifier.height(8.dp))
            NeonGauge(stats.cpuLoad ?: 0f)
            Spacer(Modifier.height(12.dp))
            Text("Used RAM: ${stats.usedRamMb?.let { "$it MB" } ?: "Unavailable"}", color = TextWhite)
        }
        Spacer(Modifier.height(12.dp))
        Text("Readings are informational. Android does not allow this app to guarantee higher game FPS.", color = MutedGrey)
    }
}
