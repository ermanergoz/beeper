package com.erman.beeper.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.erman.beeper.model.AlarmPreferences

class PreferenceProvider(private val sharedPreferences: SharedPreferences) : AlarmPreferences {
    override var isAlarmEnabled: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_ALARM_ON, true)
        set(value) = sharedPreferences.edit { putBoolean(KEY_IS_ALARM_ON, value) }

    companion object {
        // Used since version 1.0.0, renaming it would reset the choice of existing users
        const val FILE_NAME = "com.erman.beeper"
        private const val KEY_IS_ALARM_ON = "isAlarmOn"
    }
}
