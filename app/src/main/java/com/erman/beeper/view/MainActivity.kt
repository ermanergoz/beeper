package com.erman.beeper.view

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erman.beeper.R
import com.erman.beeper.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), BatteryOptimizationDialog.BatteryDialogListener {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()

        if (!isIgnoringBatteryOptimizations() && !isBatteryOptimizationDialogShown())
            BatteryOptimizationDialog().show(supportFragmentManager, BatteryOptimizationDialog.TAG)
    }

    private fun isBatteryOptimizationDialogShown(): Boolean {
        return supportFragmentManager.findFragmentByTag(BatteryOptimizationDialog.TAG) != null
    }

    private fun isIgnoringBatteryOptimizations(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val powerManager = applicationContext.getSystemService(Context.POWER_SERVICE) as PowerManager
            return powerManager.isIgnoringBatteryOptimizations(applicationContext.packageName)
        }
        return true
    }

    override fun navigateToSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                startActivity(Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS))
            } catch (e: ActivityNotFoundException) {
                // Some vendors remove this settings screen, there is nothing else to open
            }
        }
    }
}