package com.daveboostzz.app.ui.screens

import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import rikka.shizuku.Shizuku
import com.daveboostzz.app.ui.components.ScreenScaffold
import com.daveboostzz.app.ui.theme.*

@Composable
fun ShizukuScreen() {
    var serviceAvailable by remember { mutableStateOf(false) }
    var granted by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (isActive) {
            serviceAvailable = runCatching { Shizuku.pingBinder() }.getOrDefault(false)
            granted = serviceAvailable && runCatching {
                Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
            }.getOrDefault(false)
            delay(1500)
        }
    }
    ScreenScaffold("SHIZUKU CONTROL", "User-authorised service status") {
        StatusLine("Service running", serviceAvailable)
        StatusLine("Permission granted", granted)
        Spacer(Modifier.height(12.dp))
        Text("Start Shizuku yourself using Wireless Debugging or root. This screen currently checks status only; privileged operations are not implemented.", color = MutedGrey)
    }
}

@Composable
private fun StatusLine(label: String, active: Boolean) {
    Row(Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = TextWhite)
        Text(if (active) "YES" else "NO", color = if (active) SuccessGreen else NeonRed)
    }
}
