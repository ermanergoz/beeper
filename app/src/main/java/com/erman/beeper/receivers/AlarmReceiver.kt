package com.erman.beeper.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.erman.beeper.audio.BeepPlayer
import com.erman.beeper.model.AlarmRepository
import com.erman.beeper.model.ChimeType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AlarmReceiver : BroadcastReceiver(), KoinComponent {
    private val alarmRepository: AlarmRepository by inject()
    private val beepPlayer: BeepPlayer by inject()

    override fun onReceive(context: Context, intent: Intent) {
        val chimeType = ChimeType.fromKey(intent.getStringExtra(EXTRA_CHIME_TYPE)) ?: return
        if (!alarmRepository.isAlarmEnabled) return

        val pendingResult = goAsync()
        beepPlayer.play(chimeType.beepDurationMillis) { pendingResult.finish() }
        alarmRepository.onChimePlayed(chimeType)
    }

    companion object {
        private const val EXTRA_CHIME_TYPE = "alarmType"

        fun createIntent(context: Context, chimeType: ChimeType): Intent {
            return Intent(context, AlarmReceiver::class.java).putExtra(EXTRA_CHIME_TYPE, chimeType.key)
        }
    }
}
