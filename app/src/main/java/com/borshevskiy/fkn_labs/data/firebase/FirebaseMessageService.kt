package com.borshevskiy.fkn_labs.data.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.borshevskiy.fkn_labs.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessageService : FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {

        val builder = NotificationCompat.Builder(applicationContext, "Main Channel ID")
            .setContentTitle(message.notification?.title + "HELLO")
            .setContentText(message.notification?.body  + "WORLD")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val manager = NotificationManagerCompat.from(applicationContext)
        manager.createNotificationChannel(NotificationChannel(
            "Main Channel ID", "Main Channel", NotificationManager.IMPORTANCE_HIGH))
        manager.notify(1, builder)

        super.onMessageReceived(message)
    }
}