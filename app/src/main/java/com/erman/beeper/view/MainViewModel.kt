package com.erman.beeper.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erman.beeper.model.AlarmRepository

class MainViewModel(private val alarmRepository: AlarmRepository) : ViewModel() {
    private val _isAlarmEnabled = MutableLiveData(alarmRepository.isAlarmEnabled)
    val isAlarmEnabled: LiveData<Boolean> = _isAlarmEnabled

    init {
        alarmRepository.syncAlarms()
    }

    fun onAlarmToggled(enabled: Boolean) {
        if (enabled == _isAlarmEnabled.value) return

        _isAlarmEnabled.value = enabled
        alarmRepository.setAlarmEnabled(enabled)
    }
}
