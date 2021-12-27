package com.example.gamesapp.ui.listgames

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.liveData
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.gamesapp.data.entity.GameResponse
import androidx.paging.compose.items
import com.example.gamesapp.R
import com.example.gamesapp.ui.Screen
import com.example.gamesapp.ui.listgames.components.GamesListItem
import kotlin.coroutines.coroutineContext

var gridCellValue = liveData<Int> { emit(2) }

@ExperimentalFoundationApi
@Composable
fun GamesListScreen(
    navController: NavController,
    viewModel: ListGamesViewModel = hiltViewModel()
) {
    val cellValue = remember { GridLayoutCell(2) }
    var searchText by remember { mutableStateOf("") }
    val games: LazyPagingItems<GameResponse> =
        viewModel.games(searchText).collectAsLazyPagingItems()


    Box(modifier = Modifier.fillMaxSize()) {
        Column {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(4.dp)
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray
                    ),
                    label = { Text(text = "Search", color = Color.White) },
                    modifier = Modifier.width(315.dp)

                )

                Button(
                    onClick = {
                        if (cellValue.value == 2) {
                            cellValue.value = 1
                            Log.d(TAG, "GamesListScreen: " + gridCellValue)
                        } else {
                            cellValue.value = 2
                        }
                    },
                ) {


                    val imageResource =
                        if (cellValue.value != 2)
                            R.drawable.ic_smalllayoutbutton
                        else
                            R.drawable.ic_biglayoutbutton
                    Image(
                        painter = painterResource(imageResource),
                        modifier = Modifier
                            .padding(2.dp)
                            .height(35.dp),
                        contentDescription = "item"
                    )
                }
            }

            LazyVerticalGrid(
                cells = GridCells.Fixed(cellValue.value),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
            ) {
                items(games.itemCount) { index ->

                    games.get(index)?.let {
                        GamesListItem(
                            game = it,
                            onItemClick = {
                                navController.navigate(Screen.GameDetailScreen.route + "/${it.id}")
                            }
                        )
                    }
                    Divider()
                }
            }
        }
    }
}


class GridLayoutCell(value: Int) {
    var value by mutableStateOf(value)
}


