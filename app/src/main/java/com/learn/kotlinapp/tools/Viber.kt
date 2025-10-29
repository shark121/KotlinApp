package com.learn.kotlinapp.tools

import android.Manifest
import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import androidx.compose.ui.platform.LocalContext

class Listener(val context : Context):Activity() {

    override fun onCreate(instance: Bundle?){
        super.onCreate(instance)

        println("Listener init")

    }

    fun listen() :Boolean{
        val listener = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        println(listener.isMusicActive)

        return listener.isMusicActive
    }
}