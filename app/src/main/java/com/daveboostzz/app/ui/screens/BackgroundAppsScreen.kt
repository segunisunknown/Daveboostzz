package com.daveboostzz.app.ui.screens
import androidx.compose.runtime.Composable

import androidx.compose.material3.Text
import com.daveboostzz.app.ui.components.ScreenScaffold
import com.daveboostzz.app.ui.theme.MutedGrey

@Composable
fun BackgroundAppsScreen() {
    ScreenScaffold("BACKGROUND APPS", "Safe app management") {
        Text("Android manages background processes automatically. This starter build does not force-stop apps or terminate system processes.", color = MutedGrey)
    }
}
