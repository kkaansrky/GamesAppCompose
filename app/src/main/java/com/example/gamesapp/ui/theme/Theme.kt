package com.example.gamesapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.gamesapp.ui.detailgame.ui.theme.Shapes
import com.example.gamesapp.ui.detailgame.ui.theme.Typography

private val DarkColorPalette = darkColors(
    primary = Background,
    primaryVariant = CardBack,
    secondary = Teal200,
    background = Background
)

private val LightColorPalette = lightColors(
    primary = Background,
    primaryVariant = CardBack,
    secondary = Teal200,
    background = Background

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */

)

@Composable
fun GamesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}