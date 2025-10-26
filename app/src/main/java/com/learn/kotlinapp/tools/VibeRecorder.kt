package com.learn.kotlinapp.tools

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import java.io.File

class VibeRecorder(private val context: Context) {

    private var mediaRecorder: MediaRecorder? = null
    private var outputFile: String? = null

    fun startRecording() {
        // ✅ Check permission directly from Context
        if (context.checkSelfPermission(Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("VibeRecorder", "❌ Missing RECORD_AUDIO permission!")
            return
        }

        val audioFile = File(
            context.getExternalFilesDir(Environment.DIRECTORY_MUSIC),
            "audio_record_${System.currentTimeMillis()}.mp3"
        )
        outputFile = audioFile.absolutePath

        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setAudioEncodingBitRate(128000)
            setAudioSamplingRate(44100)
            setOutputFile(outputFile)
            prepare()
            start()
        }

        Log.v("VibeRecorder", "🎙️ Recording started... Saving to: $outputFile")
    }

    fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            reset()
            release()
        }
        mediaRecorder = null
        Log.v("VibeRecorder", "🛑 Recording stopped. File saved at: $outputFile")
    }
}
