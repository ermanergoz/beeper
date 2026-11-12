package com.erman.beeper.model

import java.util.Calendar
import java.util.TimeZone

fun nextChimeTime(nowMillis: Long, chimeType: ChimeType, timeZone: TimeZone = TimeZone.getDefault()): Long {
    return Calendar.getInstance(timeZone).apply {
        timeInMillis = nowMillis
        set(Calendar.MINUTE, chimeType.minuteOfHour)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        if (timeInMillis <= nowMillis) add(Calendar.HOUR_OF_DAY, 1)
    }.timeInMillis
}
