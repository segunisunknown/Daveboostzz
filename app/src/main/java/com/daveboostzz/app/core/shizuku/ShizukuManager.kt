package com.daveboostzz.app.core.shizuku

import android.content.pm.PackageManager
import rikka.shizuku.Shizuku

class ShizukuManager {
    fun isServiceAvailable(): Boolean = runCatching { Shizuku.pingBinder() }.getOrDefault(false)
    fun hasPermission(): Boolean = isServiceAvailable() &&
        runCatching { Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED }.getOrDefault(false)
    fun requestPermission(requestCode: Int = 1001) {
        if (isServiceAvailable() && !hasPermission()) {
            Shizuku.requestPermission(requestCode)
        }
    }
}
