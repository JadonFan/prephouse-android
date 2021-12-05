package com.prephouse.prephouse.components.practice

import android.view.ViewGroup
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun VideoPreview(
    onRecorder: (Recorder) -> Unit = { },
    onPreview: (UseCase) -> Unit = { }
) {
    val qualitySelector = QualitySelector
        .firstTry(QualitySelector.QUALITY_UHD)
        .thenTry(QualitySelector.QUALITY_FHD)
        .thenTry(QualitySelector.QUALITY_HD)
        .finallyTry(QualitySelector.QUALITY_SD)

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        factory = { context ->
            val previewView = PreviewView(context).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }

            onRecorder(
                Recorder.Builder()
                    .setQualitySelector(qualitySelector)
                    .build()
            )

            onPreview(
                Preview.Builder()
                    .build()
                    .also { it.setSurfaceProvider(previewView.surfaceProvider) }
            )

            previewView
        }
    )
}
