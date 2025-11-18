package com.poc.toolboxapp.presenter.screens.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.poc.domain.entities.PhotosEntity
import com.poc.toolboxapp.presenter.screens.atoms.ImageLoader
import com.poc.toolboxapp.presenter.screens.atoms.VideoLoader
import com.poc.toolboxapp.utils.extensions.DpMeasureUtils
import com.poc.toolboxapp.utils.extensions.DpMeasureUtils.IMAGE_SIZE

@Composable
fun DetailCard(modifier: Modifier, photosEntity: PhotosEntity) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(DpMeasureUtils.SMALL_PADDING.dp)
                .fillMaxHeight()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.wrapContentSize()) {
                Text(photosEntity.title)
                Spacer(modifier = Modifier.height(DpMeasureUtils.SMALL_SPACER.dp))
                ImageLoader(
                    url = photosEntity.url,
                    imageModifier = Modifier
                        .size(IMAGE_SIZE.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(DpMeasureUtils.SMALL_SPACER.dp))

            VideoLoader(modifier = Modifier.fillMaxWidth().aspectRatio(16f/9f))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    DetailCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        PhotosEntity(title = "Hello", albumId = 1, url = "https://picsum.photos/id/104/600")
    )
}