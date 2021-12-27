package com.example.gamesapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.gamesapp.ui.detailgame.ui.theme.Shapes
import com.example.gamesapp.ui.detailgame.ui.theme.Typography

private val DarkColorPalette = darkColors(
    primary = Background,
    primaryVariant = CardBack,
    secondary = Teal200,
    background = Background,
    surface = Background,
    onPrimary = Color.White,
    onSurface = Background,
)

@Composable
fun GamesAppTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}