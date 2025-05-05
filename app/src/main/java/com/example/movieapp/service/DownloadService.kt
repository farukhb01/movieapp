package com.example.movieapp.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.movieapp.R

class DownloadService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == "cancel_download") {
            stopSelf()
            return START_NOT_STICKY
        }

        val movieName = intent?.getStringExtra("movie_name") ?: "Unknown"

        val cancelIntent = Intent(this, DownloadService::class.java).apply {
            action = "cancel_download"
        }

        val cancelPendingIntent = PendingIntent.getService(
            this,
            0,
            cancelIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, "download_channel")
            .setSmallIcon(R.drawable.ic_download)
            .setContentTitle("Downloading...")
            .setContentText("Movie: $movieName")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(" Movie: $movieName")
            )
            .addAction(0, "Cancel Download", cancelPendingIntent)
            .setOnlyAlertOnce(true)
            .setOngoing(true)
            .build()

        startForeground(1, notification)

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}


