package com.daveboostzz.app.ui.screens

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.daveboostzz.app.ui.components.*
import com.daveboostzz.app.ui.theme.*

@Composable
fun NetworkScreen() {
    val context = LocalContext.current
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = cm.activeNetwork
    val caps = network?.let { cm.getNetworkCapabilities(it) }
    val type = when {
        caps?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> "Wi-Fi"
        caps?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> "Mobile data"
        caps?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true -> "Ethernet"
        else -> "Disconnected / unknown"
    }
    ScreenScaffold("NETWORK", "Connection overview") {
        CyberCard {
            Text("Connection: $type", color = TextWhite)
            Text("Internet validated: ${caps?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) == true}", color = TextWhite)
        }
        Text("This screen reports connection type only; it does not inspect traffic contents.", color = MutedGrey)
    }
}
