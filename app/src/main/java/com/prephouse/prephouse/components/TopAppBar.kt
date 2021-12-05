package com.prephouse.prephouse.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.prephouse.prephouse.R
import com.prephouse.prephouse.navigation.LocalBackPressedDispatcher
import com.prephouse.prephouse.navigation.Routes

@Composable
fun TopAppBar(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val backDispatcher = LocalBackPressedDispatcher.current

    SmallTopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        navigationIcon = {
            IconButton(
                onClick = { backDispatcher.onBackPressed() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(Routes.PROFILE)
            }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = stringResource(R.string.profile)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}
