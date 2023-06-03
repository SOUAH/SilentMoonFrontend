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
        //I need to get notification helper in order to schedule next reminder but for BroadcastReceiver it must be done like this
        //a bit of a more complex topic, will be explained later
        val appComponent = EntryPointAccessors.fromApplication(context, ReceiverEntryPoint::class.java)
        val notificationHelper = appComponent.getNotificationHelper()
        sendNotification(context)

        notificationHelper.setNextReminder()
    }

    fun sendNotification(applicationContext: Context) {
        //updating id so every notification will have unique one
        NOTIFICATION_ID++
        
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        val contentIntent = Intent(applicationContext, MainActivity::class.java)

        val contentPendingIntent = PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Build the notification
        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Silent Moon")
            .setContentText("Your meditation reminder")
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(notificationManager) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }
}