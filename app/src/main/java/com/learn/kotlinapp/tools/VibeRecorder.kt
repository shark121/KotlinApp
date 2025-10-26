package com.learn.kotlinapp.tools

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import android.util.Log
import java.io.File

open class VibeRecorder : ComponentActivity() {

    private var mediaRecorder: MediaRecorder? = null
    private var outputFile: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Request permission at runtime for Android 6.0+
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                200
            )
        }
    }

    fun startRecording() {
        // Where to save the recording
        val audioFile = File(
            getExternalFilesDir(Environment.DIRECTORY_MUSIC),
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

        Log.v( "vibe recorder","🎙️ Recording started... Saving to: $outputFile")
    }

    fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            reset()
            release()
        }
        mediaRecorder = null
        println("🛑 Recording stopped. File saved at: $outputFile")
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRecording()
    }
}
