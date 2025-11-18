package com.poc.toolboxapp.presenter.screens.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.domain.entities.PhotosEntity
import com.poc.toolboxapp.presenter.screens.atoms.ImageLoader
import com.poc.toolboxapp.utils.extensions.DpMeasureUtils
import com.poc.toolboxapp.utils.extensions.SpMeasureUtils.SMALL_TEXT_SIZE

@Composable
fun PhotosCard(modifier: Modifier, photo: PhotosEntity, imageModifier: Modifier) {
    Column(modifier = modifier) {
        Text(photo.title, style = TextStyle(
            fontSize = SMALL_TEXT_SIZE.sp,
            fontStyle = FontStyle.Italic

        ))
        Spacer(modifier = Modifier.height(DpMeasureUtils.SMALL_SPACER.dp))
        ImageLoader(photo.url, imageModifier)
    }
}