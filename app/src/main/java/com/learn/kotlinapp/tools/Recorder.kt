package com.learn.kotlinapp.tools

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.media.projection.MediaProjectionManager
import androidx.activity.result.contract.ActivityResultContracts

import android.app.Activity

open class Recorder(val context : Context): ComponentActivity() {

    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        val projectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

        val screenCaptureLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val mediaProjection = projectionManager.getMediaProjection(result.resultCode, result.data!!)

            }
        }

        screenCaptureLauncher.launch(projectionManager.createScreenCaptureIntent())
    }


    fun Record(){
          val manager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
          val captureIntent = manager.createScreenCaptureIntent()
        startActivity(captureIntent)

    }
}