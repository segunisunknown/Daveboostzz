package com.daveboostzz.app.ui.screens

import android.content.Intent
import android.provider.Settings
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import com.daveboostzz.app.ui.components.ScreenScaffold

@Composable
fun NotificationScreen() {
    val context = LocalContext.current
    ScreenScaffold("NOTIFICATIONS", "Android notification controls") {
        Button(
            onClick = { context.startActivity(Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("OPEN DAVEBOOSTZZ NOTIFICATION SETTINGS") }
        Text("Notification-listener access is not requested by this starter app.")
    }
}
