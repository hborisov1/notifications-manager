package com.blloc.notificationsmanager.ui.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class NotificationUtils(
    private val context: Context
) {

    fun createTestNotification() {
        createNotificationChannel()

        val currentDate = Date()
        val dateStr = SimpleDateFormat("dd MMM", Locale.getDefault()).format(currentDate)
        val timeStr = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(currentDate)

        val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setSmallIcon(createRandomSmallIcon())
            .setContentTitle("Notification created at: $timeStr")
            .setContentText("Notification date: $dateStr")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(System.currentTimeMillis().toInt(), builder.build())
        }
    }

    private fun createNotificationChannel() {
        val name = "test_channel_name"
        val descriptionText = "test_channel_description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    @DrawableRes
    private fun createRandomSmallIcon(): Int = when (Random.nextInt(3)) {
        0 -> android.R.drawable.ic_menu_camera
        1 -> android.R.drawable.ic_menu_compass
        2 -> android.R.drawable.ic_btn_speak_now
        3 -> android.R.drawable.ic_input_add
        else -> android.R.drawable.ic_menu_gallery
    }

}