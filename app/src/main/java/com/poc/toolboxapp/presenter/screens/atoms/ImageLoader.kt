package com.poc.toolboxapp.presenter.screens.atoms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun ImageLoader(url: String, imageModifier: Modifier) {
    AsyncImage(
        model = url,
        contentDescription = "",
        modifier = imageModifier
    )
}