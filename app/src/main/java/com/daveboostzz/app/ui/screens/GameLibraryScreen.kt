package com.daveboostzz.app.ui.screens

import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.daveboostzz.app.ui.components.ScreenScaffold
import com.daveboostzz.app.ui.theme.*

@Composable
fun GameLibraryScreen() {
    val context = LocalContext.current
    var packageName by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Enter an installed game's package name to try opening it.") }
    ScreenScaffold("GAME LIBRARY", "Launch games you choose") {
        OutlinedTextField(
            value = packageName,
            onValueChange = { packageName = it },
            label = { Text("Game package name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                val launch = context.packageManager.getLaunchIntentForPackage(packageName.trim())
                if (launch != null) {
                    context.startActivity(launch)
                    message = "Launching ${packageName.trim()}"
                } else {
                    message = "No launchable app found. Check the package name."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("LAUNCH GAME") }
        Spacer(Modifier.height(12.dp))
        Text(message, color = MutedGrey)
        Text("The app does not scan all installed apps or modify game files.", color = MutedGrey, modifier = Modifier.padding(top = 12.dp))
    }
}
