package com.poc.toolboxapp.presenter.screens.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.domain.entities.AlbumDetailedEntity
import com.poc.toolboxapp.utils.extensions.SpMeasureUtils

@Composable
fun Carrousel(albumsDetailed: List<AlbumDetailedEntity>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(albumsDetailed) { album ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(album.title, style = TextStyle(
                    fontSize = SpMeasureUtils.TITLE_TEXT_SIZE.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp,
                    color = Color.Black
                ))
            }
            LazyRow() {
                items(album.photos) { photos ->
                    PhotosCard(photos)
                }
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.secondary, modifier = Modifier.padding(20.dp))
        }
    }
}