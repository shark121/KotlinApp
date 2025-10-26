package com.learn.kotlinapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.learn.kotlinapp.tools.Listener
import com.learn.kotlinapp.tools.VibeRecorder
import com.learn.kotlinapp.ui.theme.KotlinAppTheme

open class MainActivity : ComponentActivity() {
    val isRecording = mutableStateOf<Boolean>(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   RecorderScreen(isRecording)
                }
            }
        }
    }
}



@Composable
fun RecorderScreen(isRecording: MutableState<Boolean>){
    val context = LocalContext.current
    val vibeRecorder = remember { VibeRecorder(context) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            isRecording.value = true
            vibeRecorder.startRecording()
        }
    }

    if(!isRecording.value){
        Box(modifier= Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Button(onClick = {
                        if (ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.RECORD_AUDIO
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                        } else {
                            isRecording.value = true
                            vibeRecorder.startRecording()
                        }
                    }) {
                        Text("Click to Start Recording")
                    }
                }
            }else{

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Recording In Progress", modifier = Modifier.padding(bottom = 16.dp))
            Button(onClick = {
                isRecording.value = false
                vibeRecorder.stopRecording()
            }) {
                Text("Click to Stop Recording")
            }
        }
    }


}


fun handleStartRecording(context : Context, isRecording : MutableState<Boolean>, vibeRecorder: VibeRecorder){
    isRecording.value = !isRecording.value
    vibeRecorder.startRecording()

}



fun handleStopRecording(context : Context, isRecording : MutableState<Boolean>, vibeRecorder: VibeRecorder){
    val recorder  = VibeRecorder(context)

    isRecording.value = false
    vibeRecorder.stopRecording()
//    recorder.stopRecording()

}

//
//@Preview(showBackground = true)
//@Composable
//fun AppPreview() {
//    KotlinAppTheme {
//        RecorderScreen()
//    }
//}