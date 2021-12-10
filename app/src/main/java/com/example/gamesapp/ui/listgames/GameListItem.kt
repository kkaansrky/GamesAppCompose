package com.example.gamesapp.ui.listgames.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.gamesapp.data.entity.GameResponse

@Composable
fun GamesListItem(
    game: GameResponse,
    onItemClick: (GameResponse) -> Unit
) = with(game) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
            .clickable { onItemClick(this) },
        horizontalArrangement = Arrangement.Start
    ) {
        Box(contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .background(
                    color = if (rating < 3.0) Color.Red else Color.Green,
                    shape = CircleShape
                )
                .layout() { measurable, constraints ->
                    // Measure the composable
                    val placeable = measurable.measure(constraints)

                    //get the current max dimension to assign width=height
                    val currentHeight = placeable.height
                    var heightCircle = currentHeight
                    if (placeable.width > heightCircle)
                        heightCircle = placeable.width

                    //assign the dimension and the center position
                    layout(heightCircle, heightCircle) {
                        // Where the composable gets placed
                        placeable.placeRelative(0, (heightCircle - currentHeight) / 2)
                    }
                }) {

            Text(
                text = "$rating/$ratingTop",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black
            )
        }
        Image(
            painter = rememberImagePainter(backgroundImage),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(2.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(16.dp, 0.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Released: $released",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Playtime: $playtime",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Suggestions Count: $suggestionsCount",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
            )
            SetPlatformImages(platforms.map { it.platform.name })
        }
    }
}

@Composable
fun SetPlatformImages(platformNames: List<String>) {
     for (item in platformNames) {

        val itemImageUrl = with(item.lowercase()) {
            when {
                contains("pc") -> rememberImagePainter("https://www.flaticon.com/free-icon/windows_732076?term=windows&page=1&position=1&page=1&position=1&related_id=732076&origin=search")
                else -> {
                    rememberImagePainter("https://icons.iconarchive.com/icons/martz90/circle-addon2/256/playstation-icon.png")
                }

            }
        }

        Image(painter = itemImageUrl, contentDescription = "item")
    }
}

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GamesListItem(
        GameResponse(
            1, "", "Deneme Adı", "12.01.2021", false, "ASDA",
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.mismarsanalmarket.com%2Flink-cilek-200-ml-472506&psig=AOvVaw3NwgsjhGDWzrT5QS2Tpcv3&ust=1638273185535000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCLiQsKzBvfQCFQAAAAAdAAAAABAD",
            10.0,8, emptyList(),null,null,1,
            AddedByStatus(1,1,1),null,100,
            10,"updated",null, emptyList(), emptyList(), emptyList()
        )
    )
}*/