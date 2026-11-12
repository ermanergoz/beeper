package com.erman.beeper.model

/**
 * [key] and [requestCode] identify alarms that are already
 * scheduled on the device, so they have to stay the same between releases
 */
enum class ChimeType(
    val key: String,
    val requestCode: Int,
    val minuteOfHour: Int,
    val beepDurationMillis: Int
) {
    FULL_HOUR(key = "fullHour", requestCode = 0, minuteOfHour = 0, beepDurationMillis = 500),
    HALF_HOUR(key = "halfHour", requestCode = 1, minuteOfHour = 30, beepDurationMillis = 250);

    companion object {
        fun fromKey(key: String?): ChimeType? = values().firstOrNull { it.key == key }
    }
}
