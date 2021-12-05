package com.prephouse.prephouse.components.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.prephouse.prephouse.R

@ExperimentalPermissionsApi
@Composable
fun Permission(
    permission: String,
    rationale: String,
    permissionNotAvailableContent: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
) {
    val permissionState = rememberPermissionState(permission)

    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = {
            Rationale(
                message = rationale,
                onRequestPermission = { permissionState.launchPermissionRequest() }
            )
        },
        permissionNotAvailableContent = permissionNotAvailableContent,
        content = content
    )
}

@Composable
private fun Rationale(
    message: String,
    onRequestPermission: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(stringResource(R.string.permission_request)) },
        text = { Text(message) },
        confirmButton = {
            Button(onClick = onRequestPermission) {
                Text(stringResource(R.string.ok))
            }
        }
    )
}
