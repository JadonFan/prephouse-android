package com.prephouse.prephouse.components.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prephouse.prephouse.R
import com.prephouse.prephouse.components.common.PageContainer
import com.prephouse.prephouse.utils.toggle
import com.prephouse.prephouse.viewmodels.profile.UserRegistrationViewModel

@Composable
@Preview
fun UserRegistration(viewModel: UserRegistrationViewModel = hiltViewModel()) {
    val uiState = viewModel.stateFlow.collectAsState()
    val isPasswordVisible = remember { mutableStateOf(false) }

    PageContainer {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = uiState.value.firstName,
                onValueChange = { viewModel.updateFirstName(it) },
                label = { Text(stringResource(R.string.first_name)) }
            )
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = uiState.value.lastName,
                onValueChange = { viewModel.updateLastName(it) },
                label = { Text(stringResource(R.string.last_name)) }
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.value.email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text(stringResource(R.string.email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.value.password,
            onValueChange = { viewModel.updatePassword(it) },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = if (isPasswordVisible.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (isPasswordVisible.value) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(onClick = { isPasswordVisible.toggle() }) {
                    Icon(
                        imageVector = image,
                        contentDescription = null
                    )
                }
            }
        )
        Text("Password Strength: ${stringArrayResource(R.string.password_strength)}")
        Button(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            onClick = { /*TODO*/ },
            enabled = uiState.value.isValid
        ) {
            Text(stringResource(R.string.sign_up))
        }
    }
}
