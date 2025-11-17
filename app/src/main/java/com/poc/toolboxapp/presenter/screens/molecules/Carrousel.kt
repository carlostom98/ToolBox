package com.poc.toolboxapp.presenter.screens.molecules

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.poc.domain.entities.AlbumDetailedEntity

@Composable
fun Carrousel(albumsDetailed: List<AlbumDetailedEntity>) {
    LazyColumn() {
        items(albumsDetailed) { album ->
            Text(album.title)
            LazyRow() {
                items(album.photos) { photos ->
                    PhotosCard(photos)
                }
            }
        }
    }
}