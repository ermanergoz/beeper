package com.erman.beeper.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.erman.beeper.model.AlarmRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BootReceiver : BroadcastReceiver(), KoinComponent {
    private val alarmRepository: AlarmRepository by inject()

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) alarmRepository.syncAlarms()
    }
}
