package com.prephouse.prephouse.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.camera.core.VideoCapture
import androidx.camera.video.ActiveRecording
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recorder
import androidx.core.app.ActivityCompat
import java.io.File

@SuppressLint("RestrictedApi")
fun androidx.camera.video.VideoCapture<Recorder>.record(context: Context): ActiveRecording? {
    val videoFile = File.createTempFile("Prephouse-recording-${System.currentTimeMillis()}", "mp4")

    return if (
        ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.RECORD_AUDIO
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        this.output
            .prepareRecording(
                context,
                FileOutputOptions.Builder(videoFile).build()
            )
            .withAudioEnabled()
            .start()
    } else {
        null
    }
}
