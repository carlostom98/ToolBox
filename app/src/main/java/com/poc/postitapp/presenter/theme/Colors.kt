package com.poc.postitapp.presenter.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


// Light Colors
val LightPrimary = Color(0xFFBB86FC)
val LightSecondary = Color(0xFF03DAC5)
val LightBackground = Color(0xFFFFFFFF)
val LightSurface = Color(0xFFFFFFFF)
val LightError = Color(0xFFB00020)
val LightOnPrimary = Color(0xFFFFFFFF)
val LightOnSecondary = Color(0xFF000000)
val LightOnBackground = Color(0xFF000000)
val LightOnSurface = Color(0xFF000000)
val LightOnError = Color(0xFFFFFFFF)

// Dark Colors
val DarkPrimary = Color(0xFF6200EE)
val DarkSecondary = Color(0xFF03DAC5)
val DarkBackground = Color(0xFF121212)
val DarkSurface = Color(0xFF121212)
val DarkError = Color(0xFFCF6679)
val DarkOnPrimary = Color(0xFF000000)
val DarkOnSecondary = Color(0xFF000000)
val DarkOnBackground = Color(0xFFFFFFFF)
val DarkOnSurface = Color(0xFFFFFFFF)
val DarkOnError = Color(0xFF000000)

val LightColors = lightColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    background = LightBackground,
    surface = LightSurface,
    error = LightError,
    onPrimary = LightOnPrimary,
    onSecondary = LightOnSecondary,
    onBackground = LightOnBackground,
    onSurface = LightOnSurface,
    onError = LightOnError
)

val DarkColors = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    background = DarkBackground,
    surface = DarkSurface,
    error = DarkError,
    onPrimary = DarkOnPrimary,
    onSecondary = DarkOnSecondary,
    onBackground = DarkOnBackground,
    onSurface = DarkOnSurface,
    onError = DarkOnError
)