package com.erman.beeper.app.di

import android.content.Context
import com.erman.beeper.audio.BeepPlayer
import com.erman.beeper.data.AlarmManagerScheduler
import com.erman.beeper.data.PreferenceProvider
import com.erman.beeper.model.AlarmRepository
import com.erman.beeper.model.AlarmPreferences
import com.erman.beeper.model.AlarmScheduler
import com.erman.beeper.view.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AlarmPreferences> {
        PreferenceProvider(androidContext().getSharedPreferences(PreferenceProvider.FILE_NAME, Context.MODE_PRIVATE))
    }
    single<AlarmScheduler> { AlarmManagerScheduler(androidContext()) }
    single { AlarmRepository(get(), get()) }
    factory { BeepPlayer() }
    viewModel { MainViewModel(get()) }
}
