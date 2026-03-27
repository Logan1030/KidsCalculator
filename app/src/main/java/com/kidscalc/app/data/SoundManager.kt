package com.kidscalc.app.data

import android.content.Context
import android.media.ToneGenerator
import android.media.AudioManager
import android.speech.tts.TextToSpeech
import java.util.Locale

class SoundManager(private val context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var isTtsReady = false
    private val toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)

    init {
        tts = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.CHINESE)
            isTtsReady = result != TextToSpeech.LANG_MISSING_DATA &&
                         result != TextToSpeech.LANG_NOT_SUPPORTED
        }
    }

    fun playCorrect() {
        if (isTtsReady) {
            tts?.speak("太棒了！", TextToSpeech.QUEUE_FLUSH, null, "correct")
        } else {
            toneGenerator.startTone(ToneGenerator.TONE_PROP_ACK, 150)
        }
    }

    fun playIncorrect() {
        if (isTtsReady) {
            tts?.speak("再试试看", TextToSpeech.QUEUE_FLUSH, null, "incorrect")
        } else {
            toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 200)
        }
    }

    fun release() {
        tts?.stop()
        tts?.shutdown()
        toneGenerator.release()
    }
}
