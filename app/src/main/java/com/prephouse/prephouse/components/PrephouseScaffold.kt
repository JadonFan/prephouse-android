package com.prephouse.prephouse.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.prephouse.prephouse.R
import com.prephouse.prephouse.themes.prephouseDarkColorScheme
import com.prephouse.prephouse.themes.prephouseLightColorScheme

@ExperimentalMaterial3Api
@Composable
fun PrephouseScaffold(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (PaddingValues) -> Unit
) {
    MaterialTheme(colorScheme = if (darkTheme) prephouseDarkColorScheme else prephouseLightColorScheme) {
        Scaffold(
            topBar = { TopAppBar(navController, scrollBehavior) },
            bottomBar = { BottomNavBar(navController) },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text(stringResource(R.string.practice)) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Create,
                            contentDescription = null
                        )
                    },
                    onClick = { }
                )
            },
            content = content
        )
    }
}
