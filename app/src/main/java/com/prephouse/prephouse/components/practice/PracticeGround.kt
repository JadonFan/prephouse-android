package com.prephouse.prephouse.components.practice

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.prephouse.prephouse.components.common.Permission

@ExperimentalPermissionsApi
@Composable
@Preview
fun PracticeGround() {
    Permission(
        permission = android.Manifest.permission.CAMERA,
        rationale = "The front camera is required to record an interview or presentation on the app"
    ) {
        VideoPreview()
    }
}
