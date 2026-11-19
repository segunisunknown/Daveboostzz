package com.daveboostzz.app.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.daveboostzz.app.core.device.DeviceStatsRepository
import com.daveboostzz.app.ui.screens.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun DaveNavHost(
    selected: String,
    onNavigate: (String) -> Unit,
    onStartOverlay: () -> Unit,
    onStopOverlay: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val repository = remember { DeviceStatsRepository(context) }
    DisposableEffect(repository) { onDispose { repository.close() } }

    Box(modifier.fillMaxSize()) {
        when (selected) {
            "Dashboard" -> DashboardScreen(repository, onNavigate, onStartOverlay)
            "Performance" -> PerformanceScreen(repository)
            "Games" -> GameLibraryScreen()
            "Settings" -> SettingsScreen(onStartOverlay, onStopOverlay)
            "Shizuku" -> ShizukuScreen()
            "Battery" -> BatteryScreen(repository)
            "Network" -> NetworkScreen()
            "Background Apps" -> BackgroundAppsScreen()
            "Notifications" -> NotificationScreen()
            "Statistics" -> StatisticsScreen(repository)
        }
    }
}
