package com.daveboostzz.app.core.device

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.Closeable
import java.io.File

data class DeviceSnapshot(
    val cpuLoad: Float? = null,
    val usedRamMb: Long? = null,
    val batteryPercent: Int = 0,
    val batteryTempC: Float? = null
)

class DeviceStatsRepository(context: Context) : Closeable {
    private val appContext = context.applicationContext
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val _snapshot = MutableStateFlow(DeviceSnapshot())
    val snapshot: StateFlow<DeviceSnapshot> = _snapshot.asStateFlow()

    init {
        scope.launch {
            while (isActive) {
                runCatching { readSnapshot() }.getOrNull()?.let { _snapshot.value = it }
                delay(2000)
            }
        }
    }

    private suspend fun readSnapshot(): DeviceSnapshot {
        val cpu = readCpuLoad()
        val memory = ActivityManager.MemoryInfo()
        val activityManager = appContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.getMemoryInfo(memory)
        val usedRam = ((memory.totalMem - memory.availMem).coerceAtLeast(0L)) / (1024 * 1024)
        val battery = appContext.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = battery?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = battery?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val percent = if (level >= 0 && scale > 0) (level * 100 / scale).coerceIn(0, 100) else 0
        val temp = battery?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) ?: -1
        return DeviceSnapshot(cpu, usedRam, percent, if (temp >= 0) temp / 10f else null)
    }

    private suspend fun readCpuLoad(): Float? {
        fun sample(): LongArray? = runCatching {
            val fields = File("/proc/stat").bufferedReader().use { it.readLine() }
                .trim().split(Regex("\\s+"))
            val values = fields.drop(1).take(8).map { it.toLong() }
            if (values.size < 4) return null
            val idle = values[3] + (values.getOrNull(4) ?: 0L)
            longArrayOf(values.sum(), idle)
        }.getOrNull()
        val first = sample() ?: return null
        delay(250)
        val second = sample() ?: return null
        val total = second[0] - first[0]
        val idle = second[1] - first[1]
        if (total <= 0) return null
        return ((total - idle).toFloat() / total).coerceIn(0f, 1f)
    }

    override fun close() { scope.cancel() }
}
