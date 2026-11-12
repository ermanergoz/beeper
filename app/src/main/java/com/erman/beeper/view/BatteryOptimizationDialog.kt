package com.erman.beeper.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.erman.beeper.R

class BatteryOptimizationDialog : DialogFragment() {
    private lateinit var listener: BatteryDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? BatteryDialogListener
            ?: throw IllegalStateException("$context must implement BatteryDialogListener")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage(R.string.turn_off_battery_optimization)
            .setPositiveButton(R.string.ok) { _, _ -> listener.navigateToSettings() }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    interface BatteryDialogListener {
        fun navigateToSettings()
    }

    companion object {
        const val TAG = "BatteryOptimizationDialog"
    }
}
