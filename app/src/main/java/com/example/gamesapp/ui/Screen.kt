package com.example.gamesapp.ui

sealed class Screen(val route: String) {
    object GameListScreen : Screen("games_list_screen")
    object GameDetailScreen: Screen("game_detail_screen")
}