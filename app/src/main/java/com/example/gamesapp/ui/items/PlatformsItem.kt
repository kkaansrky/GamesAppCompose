package com.example.gamesapp.ui.listgames

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gamesapp.R
import com.example.gamesapp.data.entity.game.Platforms


val platformImageResourceList = ArrayList<Int>()

@Composable
fun SetPlatformImages(platforms: List<Platforms>) {

    getPlatformImagesResourceFromStringList(platforms)


    Row {
        for (item in platformImageResourceList){
            Image(
                painter = painterResource(item),
                modifier = Modifier
                    .padding(2.dp)
                ,
                contentDescription = "item")
        }
    }
}

private fun setImageResourceList(imageResource: Int){
    if (!platformImageResourceList.contains(imageResource)){
        platformImageResourceList.add(imageResource)
    }
}

private fun getPlatformImagesResourceFromStringList(platforms: List<Platforms>){
    val platformNames = platforms.map { it.platform.name }

    for (item in platformNames) {
       with(item.lowercase()) {
            when {
                contains("pc") -> setImageResourceList(R.drawable.ic_windows)
                contains("playstation") -> setImageResourceList(R.drawable.ic_playstation)
                contains("xbox") -> setImageResourceList(R.drawable.ic_xboxone)
                contains("amiga") -> setImageResourceList(R.drawable.ic_amiga)
                contains("android") -> setImageResourceList(R.drawable.ic_android)
                contains("atari") -> setImageResourceList(R.drawable.ic_atari)
                contains("linux") -> setImageResourceList(R.drawable.ic_linux)
                contains("mobile") -> setImageResourceList(R.drawable.ic_mobile)
                contains("sega") -> setImageResourceList(R.drawable.ic_sega)
                contains("switch") -> setImageResourceList(R.drawable.ic_switch)
                else -> setImageResourceList(R.drawable.ic_mobile)
            }
        }
    }
}