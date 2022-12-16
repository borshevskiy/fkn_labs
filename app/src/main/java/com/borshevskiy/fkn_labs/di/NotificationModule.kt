package com.borshevskiy.fkn_labs.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.borshevskiy.fkn_labs.R
import com.borshevskiy.fkn_labs.MainActivity
import com.borshevskiy.fkn_labs.presentation.navigation.Screen.Companion.FROM_NOTIFICATION
import com.borshevskiy.fkn_labs.presentation.navigation.Screen.Companion.HERO_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @OptIn(ExperimentalSnapperApi::class)
    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {
        val clickIntent = Intent(Intent.ACTION_VIEW, "$FROM_NOTIFICATION/$HERO_ID=1009268".toUri(),context, MainActivity::class.java)
        val clickPendingIntent = PendingIntent.getActivity(context, 1, clickIntent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(context, "Main Channel ID")
            .setContentTitle("Welcome")
            .setContentText("YouTube Channel: Stevdza-San")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setPublicVersion(
                NotificationCompat.Builder(context, "Main Channel ID")
                    .setContentTitle("Hidden")
                    .setContentText("Unlock to see the message.")
                    .build()
            )
            .setContentIntent(clickPendingIntent)
    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Main Channel ID",
                "Main Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        return notificationManager
    }

}