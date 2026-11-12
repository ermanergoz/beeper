package com.erman.beeper.audio

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.Looper

class BeepPlayer {
    fun play(durationMillis: Int, onFinished: () -> Unit) {
        val toneGenerator = try {
            ToneGenerator(AudioManager.STREAM_MUSIC, ToneGenerator.MAX_VOLUME)
        } catch (e: RuntimeException) {
            // The audio system may refuse to create a tone generator, skip this beep
            onFinished()
            return
        }

        toneGenerator.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, durationMillis)

        Handler(Looper.getMainLooper()).postDelayed({
            toneGenerator.release()
            onFinished()
        }, durationMillis + TONE_RELEASE_MARGIN_MILLIS)
    }

    companion object {
        private const val TONE_RELEASE_MARGIN_MILLIS = 100L
    }
}
