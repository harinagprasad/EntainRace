package com.app.nexttogo.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.app.nexttogo.presentation.navigation.AppNavHost
import com.app.nexttogo.presentation.navigation.Destination
import com.app.nexttogo.presentation.navigation.composable
import com.app.nexttogo.presentation.theme.NextToGoTheme
import com.app.nexttogo.presentation.ui.racesList.RaceListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NextToGoTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    AppNavHost(
                        navController = rememberNavController(),
                        startDestination = Destination.RaceList,
                        builder = {
                            composable(destination = Destination.RaceList) {
                                RaceListScreen(hiltViewModel())
                            }
                        }
                    )
                }
            }
        }
    }
}