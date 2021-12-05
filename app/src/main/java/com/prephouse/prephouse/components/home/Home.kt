package com.prephouse.prephouse.components.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prephouse.prephouse.R
import com.prephouse.prephouse.components.common.PageContainer

@Composable
@Preview
fun Home() {
    val scrollState = rememberScrollState()

    PageContainer(
        modifier = Modifier.verticalScroll(scrollState),
    ) {
        Text(
            text = stringResource(R.string.welcome),
            modifier = Modifier.padding(16.dp)
        )
    }
}
