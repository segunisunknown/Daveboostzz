package com.daveboostzz.app.overlays

import android.app.*
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView

class FpsOverlayService : Service() {
    private var windowManager: WindowManager? = null
    private var label: TextView? = null

    override fun onCreate() {
        super.onCreate()

        if (!Settings.canDrawOverlays(this)) {
            stopSelf()
            return
        }

        val channelId = "daveboostzz_overlay"

        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                channelId,
                "Performance overlay",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }

        val notification = Notification.Builder(this, channelId)
            .setContentTitle("DAVEBOOSTZZ overlay active")
            .setContentText("FPS measurement is not available yet.")
            .setSmallIcon(android.R.drawable.ic_menu_view)
            .build()

        startForeground(42, notification)

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        label = TextView(this).apply {
            text = "FPS: N/A"
            setTextColor(Color.WHITE)
            setBackgroundColor(0xCC111111.toInt())
            textSize = 14f
            setPadding(16, 10, 16, 10)
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.END
            x = 20
            y = 120
        }

        runCatching {
            windowManager?.addView(label, params)
        }.onFailure {
            stopSelf()
        }
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int = START_NOT_STICKY

    override fun onDestroy() {
        runCatching {
            label?.let { windowManager?.removeView(it) }
        }
        label = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
