package com.crs.bluered

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.crs.bluered.ui.navigation.RootHost
import com.crs.bluered.ui.theme.BlueRedTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        installSplashScreen().setKeepOnScreenCondition { viewModel.isSplashLoading.value }

        setContent {
            BlueRedTheme {

                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                val navController: NavHostController = rememberNavController()

                RootHost(
                    startDestination = uiState.startDestination,
                    navController = navController
                )
            }
        }
    }
}
