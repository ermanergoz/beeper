package com.erman.beeper.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import com.erman.beeper.model.AlarmScheduler
import com.erman.beeper.model.ChimeType
import com.erman.beeper.receivers.AlarmReceiver

class AlarmManagerScheduler(private val context: Context) : AlarmScheduler {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun schedule(chimeType: ChimeType, triggerAtMillis: Long) {
        val intent = pendingIntentFor(chimeType)

        when {
            //setRepeating causes problems in doze mode
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ->
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, intent)
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ->
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, intent)
            else ->
                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, intent)
        }
    }

    override fun cancel(chimeType: ChimeType) {
        alarmManager.cancel(pendingIntentFor(chimeType))
    }

    private fun pendingIntentFor(chimeType: ChimeType): PendingIntent {
        val intent = AlarmReceiver.createIntent(context, chimeType)
        return PendingIntent.getBroadcast(context, chimeType.requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}
