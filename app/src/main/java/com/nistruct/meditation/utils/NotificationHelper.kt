package com.nistruct.meditation.utils

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.nistruct.meditation.utils.Constants.CHANNEL_ID
import com.nistruct.meditation.utils.Constants.CHANNEL_NAME
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

class NotificationHelper @Inject constructor(
    val applicationContext: Context
) {
    init {
        createNotificationChannel()
    }

    lateinit var reminderTime: LocalTime
    lateinit var reminderDays: Array<String>

    fun startScheduler(reminderTime: LocalTime, reminderDays: Array<String>){
        this.reminderTime = reminderTime
        this.reminderDays = reminderDays

        setNextReminder()
    }

    fun setNextReminder() {
        if(reminderDays.isEmpty()){
            return
        }

        var reminderDateTime = getNextReminderDateTime(reminderTime, reminderDays)
        scheduleReminder(reminderDateTime)
    }

    private fun createNotificationChannel() {
        val name = CHANNEL_NAME
        val descriptionText = "Channel description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun getNextReminderDateTime(reminderTime: LocalTime, reminderDays: Array<String>): LocalDateTime {
        val now = LocalDateTime.now()
        val dayOfWeekNow = now.dayOfWeek.value

        val reminderDaysOfWeek = reminderDays.map { DayOfWeek.valueOf(it.toUpperCase()).value }.sorted()

        for (reminderDay in reminderDaysOfWeek) {
            if (reminderDay == dayOfWeekNow && LocalTime.now().isBefore(reminderTime)) {
                return LocalDateTime.of(now.toLocalDate(), reminderTime)
            } else if (reminderDay > dayOfWeekNow) {
                val daysUntilNextReminder = reminderDay - dayOfWeekNow
                return LocalDateTime.of(now.plusDays(daysUntilNextReminder.toLong()).toLocalDate(), reminderTime)
            }
        }

        // If no suitable day was found in this week, return the closest day in the next week
        val daysUntilNextReminder = 7 - (dayOfWeekNow - reminderDaysOfWeek.first())
        return LocalDateTime.of(now.plusDays(daysUntilNextReminder.toLong()).toLocalDate(), reminderTime)
    }

    private fun scheduleReminder(targetTime: LocalDateTime) {
        val alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(applicationContext, Scheduler::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val targetTimeInMillis = targetTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, targetTimeInMillis, pendingIntent)
    }
}
