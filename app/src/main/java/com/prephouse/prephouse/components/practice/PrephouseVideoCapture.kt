package com.prephouse.prephouse.components.practice

import android.annotation.SuppressLint
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.video.Recorder
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import com.prephouse.prephouse.R
import com.prephouse.prephouse.components.common.PageContainer
import com.prephouse.prephouse.utils.getCameraProvider
import com.prephouse.prephouse.utils.record

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun PrephouseVideoCapture() {
    val context = LocalContext.current

    PageContainer {
        val lifecycleOwner = LocalLifecycleOwner.current

        val recorderState = remember { mutableStateOf(Recorder.Builder().build()) }
        val previewState = remember { mutableStateOf<UseCase>(Preview.Builder().build()) }

        val videoCapture = androidx.camera.video.VideoCapture.withOutput(recorderState.value)

        VideoPreview(
            onRecorder = { recorderState.value = it },
            onPreview = { previewState.value = it }
        )
        Button(onClick = {
            videoCapture.record(context)
        }) {
            Text(stringResource(R.string.start_interview))
        }

        LaunchedEffect(previewState) {
            val cameraProvider = context.getCameraProvider()

            /*
            val cameraInfo = cameraProvider.availableCameraInfos.singleOrNull {
                Camera2CameraInfo.from(it).getCameraCharacteristic(
                    CameraCharacteristics.LENS_FACING
                ) == CameraMetadata.LENS_FACING_BACK
            } ?: return@LaunchedEffect

            val supportedQualities = QualitySelector.getSupportedQualities(cameraInfo)
            val filteredQualities = listOf(
                QualitySelector.QUALITY_UHD,
                QualitySelector.QUALITY_FHD,
                QualitySelector.QUALITY_HD,
                QualitySelector.QUALITY_SD
            ).filter { supportedQualities.contains(it) }
             */

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_FRONT_CAMERA,
                    videoCapture,
                    previewState.value
                )
            } catch (_: Exception) {
                // do nothing
            }
        }
    }
}
