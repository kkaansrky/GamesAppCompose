package com.example.gamesapp.ui.listgames

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.gamesapp.data.entity.GameResponse
import androidx.paging.compose.items
import com.example.gamesapp.ui.Screen
import com.example.gamesapp.ui.listgames.components.GamesListItem

@Composable
fun GamesListScreen (
    navController: NavController,
    viewModel: ListGamesViewModel = hiltViewModel()){
    val games : LazyPagingItems<GameResponse> =
        viewModel.games.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            items(games) { game ->
                game?.let {
                    GamesListItem(
                        game = game,
                        onItemClick = {
                            navController.navigate(Screen.GameDetailScreen.route + "/${game.id}")
                        }
                    )
                    Divider()
                }
            }
        }
    }
}