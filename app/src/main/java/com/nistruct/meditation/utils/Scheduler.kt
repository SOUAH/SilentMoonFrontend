package com.nistruct.meditation.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.nistruct.meditation.MainActivity
import com.nistruct.meditation.R
import com.nistruct.meditation.utils.Constants.CHANNEL_ID
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ReceiverEntryPoint {
    fun getNotificationHelper(): NotificationHelper
}

class Scheduler : BroadcastReceiver() {

    var NOTIFICATION_ID = 0

    override fun onReceive(context: Context, intent: Intent) {
        val appComponent = EntryPointAccessors.fromApplication(context, ReceiverEntryPoint::class.java)
        val notificationHelper = appComponent.getNotificationHelper()
        sendNotification(context)

        notificationHelper.setNextReminder()
    }

    fun sendNotification(applicationContext: Context) {
        NOTIFICATION_ID++
        
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        val contentIntent = Intent(applicationContext, MainActivity::class.java)

        // TODO: Step 1.12 create PendingIntent
        val contentPendingIntent = PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Build the notification
        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            // TODO: Step 1.3 set title, text and icon to builder
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("title")
            .setContentText("body")
            // TODO: Step 1.13 set content intent
            .setContentIntent(contentPendingIntent)

            // TODO: Step 2.5 set priority
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(notificationManager) {
            // notificationId is a unique int for each notification that you must define
            notify(NOTIFICATION_ID, builder.build())
        }
    }
}