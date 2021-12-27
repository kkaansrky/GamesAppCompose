package com.example.gamesapp.ui.listgames.components

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import coil.compose.rememberImagePainter
import com.example.gamesapp.data.entity.GameResponse
import com.example.gamesapp.ui.theme.CardBack
import com.example.gamesapp.R
import com.example.gamesapp.ui.listgames.SetPlatformImages
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun GamesListItem(
    game: GameResponse,
    onItemClick: (GameResponse) -> Unit
) = with(game) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = CardBack,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 4.dp)
                .clickable { onItemClick(this) },
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = rememberImagePainter(backgroundImage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(20.dp))

            FlowRow {
                SetPlatformImages(platforms)
            }
        }
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