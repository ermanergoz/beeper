package com.erman.beeper.model

class AlarmRepository(
    private val alarmPreferences: AlarmPreferences,
    private val alarmScheduler: AlarmScheduler,
    private val currentTimeMillis: () -> Long = System::currentTimeMillis
) {
    val isAlarmEnabled: Boolean
        get() = alarmPreferences.isAlarmEnabled

    fun setAlarmEnabled(enabled: Boolean) {
        alarmPreferences.isAlarmEnabled = enabled
        syncAlarms()
    }

    fun syncAlarms() {
        ChimeType.values().forEach { chimeType ->
            if (isAlarmEnabled) scheduleNext(chimeType)
            else alarmScheduler.cancel(chimeType)
        }
    }

    fun onChimePlayed(chimeType: ChimeType) {
        if (isAlarmEnabled) scheduleNext(chimeType)
    }

    private fun scheduleNext(chimeType: ChimeType) {
        alarmScheduler.schedule(chimeType, nextChimeTime(currentTimeMillis(), chimeType))
    }
}
