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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.gamesapp.R
import com.example.gamesapp.data.entity.GameResponse
import com.example.gamesapp.data.entity.game.Genres
import com.example.gamesapp.data.entity.game.Publishers
import com.example.gamesapp.ui.listgames.SetPlatformImages
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
            .height(250.dp),
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

        SetPlatformImages(game.platforms)

        Spacer(modifier = Modifier.height(8.dp))

        GameSpecifications(game)
        //VisitWebSites()
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
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.h5
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Release Date :",
                    fontStyle = FontStyle.Italic
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
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = mapAndSetGenres(game.genres),
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
                    fontStyle = FontStyle.Italic
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
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = mapAndSetPublishers(game.publishers),
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
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.h5
            )
            Text(
                text = description.convertFromHtml(),
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun VisitWebSites() {
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
                text = "Web Connections",
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.h5
            )

            Row(
                modifier = Modifier
                    .padding(0.dp, 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Visit Reddit")

                Image(
                    painter = painterResource(R.drawable.ic_arrow),
                    modifier = Modifier
                        .padding(2.dp)
                        .height(15.dp),
                    contentDescription = "item"
                )
            }

            Divider()

            Row(
                modifier = Modifier
                    .padding(0.dp, 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Visit Website")

                Image(
                    painter = painterResource(R.drawable.ic_arrow),
                    modifier = Modifier
                        .padding(2.dp)
                        .height(15.dp)
                    ,
                    contentDescription = "item")
            }
        }
    }
}

private fun mapAndSetGenres(genres: List<Genres>): String {
    val genresNameMapped = genres.map { it.name }

    return if (genresNameMapped.isEmpty()) {
        "Unknown"
    } else {
        genresNameMapped.joinToString(
            ", ",
            "",
            "",
            -1,
            "...",
            null
        )
    }
}

private fun mapAndSetPublishers(publishers: List<Publishers>): String {
    val publishersNameMapped = publishers.map { it.name }

    return if (publishersNameMapped.isEmpty()) {
        "Unknown"
    } else {
        publishersNameMapped.joinToString(
            ", ",
            "",
            "",
            -1,
            "...",
            null
        )

    }
}
