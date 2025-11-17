package com.poc.toolboxapp.presenter.screens.atoms

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.poc.toolboxapp.utils.extensions.DpMeasureUtils

@Composable
fun ImageLoader(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "",
        modifier = Modifier.size(DpMeasureUtils.IMAGE_SIZE.dp).clip(CircleShape)
    )
}