package com.prephouse.prephouse.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.prephouse.prephouse.R

sealed class BottomNavDestination(
    val route: String,
    @StringRes val nameResId: Int,
    val icon: ImageVector
) {
    object Home : BottomNavDestination(Routes.HOME, R.string.home, Icons.Filled.Home)
    object ProgressBoard : BottomNavDestination(Routes.DASHBOARD, R.string.dashboard, Icons.Filled.Star)
    object Preferences : BottomNavDestination(Routes.PREFERENCES, R.string.preferences, Icons.Filled.Settings)
}

val bottomNavDestinations = listOf(
    BottomNavDestination.Home,
    BottomNavDestination.ProgressBoard,
    BottomNavDestination.Preferences
)
