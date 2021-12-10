package com.example.gamesapp.ui.detailgame

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.gamesapp.data.entity.GameResponse
import com.example.gamesapp.ui.theme.CardBack
import com.example.gamesapp.utils.convertFromHtml

@Composable
fun GameDetailScreen(
    viewModel: DetailGameViewModel = hiltViewModel()
) {
    val game = viewModel.game.observeAsState().value?.data
    if (game != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(0.dp, 4.dp)
        ) {
            GameInit(game)
        }
    }
}

@Composable
fun GameInit(game: GameResponse) {
    Image(
        painter = rememberImagePainter(game.backgroundImage),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(0.dp)),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .padding(10.dp, 4.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = game.name,
            style = MaterialTheme.typography.h4,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))

        GameSpecifications(game)
        GameDescription(description = game.description)
    }
}

@Composable
fun GameSpecifications(
    game: GameResponse
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = CardBack,
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Information",
                style = MaterialTheme.typography.h5
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Release Date :",
                )
                Text(
                    text = game.released,
                )
            }

            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Genres :",
                )
                Text(
                    text = game.name,
                )
            }

            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Play Time :",
                )
                Text(
                    text = game.playtime.toString(),
                    textAlign = TextAlign.End,
                )
            }

            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Publishers :",
                )
                Text(
                    text = game.reviewsTextCount.toString(),
                )
            }
        }
    }
}

@Composable
fun GameDescription(
    description: String
) {

    Card(
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = CardBack,
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Description",
                style = MaterialTheme.typography.h5
            )
            Text(
                text = description.convertFromHtml(),
                style = MaterialTheme.typography.body1
            )
        }
    }
}
