package com.prephouse.prephouse

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prephouse.prephouse.components.PrephouseScaffold
import com.prephouse.prephouse.components.dashboard.Dashboard
import com.prephouse.prephouse.components.home.Home
import com.prephouse.prephouse.components.preferences.Preferences
import com.prephouse.prephouse.components.profile.UserRegistration
import com.prephouse.prephouse.navigation.LocalBackPressedDispatcher
import com.prephouse.prephouse.navigation.Routes
import com.prephouse.prephouse.navigation.bottomNavDestinations
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContent {
            val navController = rememberNavController()

            val decayAnimationSpec = rememberSplineBasedDecay<Float>()
            val scrollBehavior = remember(decayAnimationSpec) {
                TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
            }

            CompositionLocalProvider(
                LocalBackPressedDispatcher provides this.onBackPressedDispatcher
            ) {
                PrephouseScaffold(navController = navController, scrollBehavior = scrollBehavior) {
                    NavHost(navController = navController, startDestination = bottomNavDestinations.first().route) {
                        bottomNavDestinations.forEach { destination ->
                            composable(destination.route) {
                                when (destination.route) {
                                    Routes.HOME -> Home()
                                    Routes.DASHBOARD -> Dashboard()
                                    Routes.PREFERENCES -> Preferences()
                                }
                            }
                        }
                        composable(Routes.PROFILE) { UserRegistration() }
                    }
                }
            }
        }
    }
}
