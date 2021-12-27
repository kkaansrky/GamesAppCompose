package com.example.gamesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamesapp.ui.Screen
import com.example.gamesapp.ui.detailgame.GameDetailScreen
import com.example.gamesapp.ui.listgames.GamesListScreen
import com.example.gamesapp.ui.theme.Background
import com.example.gamesapp.ui.theme.GamesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.GameListScreen.route
                    ) {
                        composable(
                            route = Screen.GameListScreen.route
                        ) {
                            GamesListScreen(navController)
                        }
                        composable(
                            route = Screen.GameDetailScreen.route + "/{gameId}"
                        ) {
                            GameDetailScreen()
                        }

                    }
                }
            }
        }
    }
}
