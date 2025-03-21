package com.justadev.underwaterworldtesttask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.justadev.underwaterworldtesttask.core.theme.UnderwaterWorldTestTaskTheme
import com.justadev.underwaterworldtesttask.presentation.navigation.GameNavigation
import com.justadev.underwaterworldtesttask.presentation.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            val navigator = Navigator(navHostController)
            UnderwaterWorldTestTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)) {
                        GameNavigation(navHostController, navigator)
                    }
                }
            }
        }
    }
}


