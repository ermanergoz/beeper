package com.erman.beeper.model

interface AlarmScheduler {
    fun schedule(chimeType: ChimeType, triggerAtMillis: Long)

    fun cancel(chimeType: ChimeType)
}
