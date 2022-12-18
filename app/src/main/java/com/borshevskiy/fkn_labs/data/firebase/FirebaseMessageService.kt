package com.borshevskiy.fkn_labs.data.firebase

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.borshevskiy.fkn_labs.MainActivity
import com.borshevskiy.fkn_labs.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dev.chrisbanes.snapper.ExperimentalSnapperApi

@ExperimentalSnapperApi
@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseMessageService : FirebaseMessagingService() {

    companion object {
        const val MARVEL_URI = "https://marvel.com/"
        const val HERO_ID = "heroID"
        const val CHANNEL_ID = "heroIdChannel"
        const val CHANNEL_NAME = "notificationChannel"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        if (message.data.isNotEmpty() && message.data.containsKey(HERO_ID)) {
            val clickIntent = Intent(Intent.ACTION_VIEW, "${MARVEL_URI}/${HERO_ID}=${message.data[HERO_ID]}".toUri(), applicationContext, MainActivity::class.java)
            val clickPendingIntent: PendingIntent = TaskStackBuilder.create(applicationContext).run {
                addNextIntentWithParentStack(clickIntent)
                getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE)
            }

            val notificationBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setContentTitle(message.notification?.title)
                .setContentText(message.notification?.body)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(clickPendingIntent)

            val notificationManager = NotificationManagerCompat.from(applicationContext)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT))
            }
            notificationManager.notify(1, notificationBuilder.build())
        }
        super.onMessageReceived(message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}