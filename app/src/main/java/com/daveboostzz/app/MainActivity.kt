package com.daveboostzz.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.daveboostzz.app.overlays.FpsOverlayService
import com.daveboostzz.app.ui.navigation.DaveNavHost
import com.daveboostzz.app.ui.theme.DaveBoostTheme

data class AppDestination(val title: String, val icon: ImageVector)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaveBoostTheme {
                var selected by remember { mutableStateOf("Dashboard") }
                val destinations = listOf(
                    AppDestination("Dashboard", Icons.Default.Dashboard),
                    AppDestination("Performance", Icons.Default.Speed),
                    AppDestination("Games", Icons.Default.SportsEsports),
                    AppDestination("Settings", Icons.Default.Settings)
                )
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            destinations.forEach { item ->
                                NavigationBarItem(
                                    selected = selected == item.title,
                                    onClick = { selected = item.title },
                                    icon = { Icon(item.icon, contentDescription = item.title) },
                                    label = { Text(item.title) }
                                )
                            }
                        }
                    }
                ) { padding ->
                    DaveNavHost(
                        selected = selected,
                        onNavigate = { selected = it },
                        onStartOverlay = { startFpsOverlay() },
                        onStopOverlay = { stopService(Intent(this, FpsOverlayService::class.java)) },
                        modifier = androidx.compose.ui.Modifier.padding(padding)
                    )
                }
            }
        }
    }

    private fun startFpsOverlay() {
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivity(intent)
            return
        }
        val intent = Intent(this, FpsOverlayService::class.java)
        androidx.core.content.ContextCompat.startForegroundService(this, intent)
    }
}
