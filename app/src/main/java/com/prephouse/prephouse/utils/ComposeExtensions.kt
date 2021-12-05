package com.prephouse.prephouse.utils

import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

fun MutableState<Boolean>.toggle() {
    value = !value
}

@Composable
@ReadOnlyComposable
fun quantityStringResource(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any): String {
    return LocalContext.current.resources.getQuantityString(id, quantity, *formatArgs)
}
