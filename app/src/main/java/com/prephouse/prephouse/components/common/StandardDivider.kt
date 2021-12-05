package com.prephouse.prephouse.components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun StandardDivider() = Divider(
    modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
)
