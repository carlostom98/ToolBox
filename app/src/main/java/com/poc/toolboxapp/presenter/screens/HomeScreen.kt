package com.poc.toolboxapp.presenter.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.poc.domain.entities.AlbumDetailedEntity
import com.poc.domain.entities.PhotosEntity
import com.poc.domain.entities.Response
import com.poc.toolboxapp.presenter.screens.atoms.ImageLoader
import com.poc.toolboxapp.presenter.screens.molecules.Carrousel
import com.poc.viewmodel.viewintents.crudintent.GetAlbumsDetailsInfoViewModel

@Composable
fun HomeScreen(getAlbumsDetailsInfoViewModel: GetAlbumsDetailsInfoViewModel, onItemClick: (PhotosEntity) -> Unit) {

    val albumsDetailedInfo: Response<List<AlbumDetailedEntity>> = getAlbumsDetailsInfoViewModel.response.collectAsState().value

    when(albumsDetailedInfo) {
        is Response.Error -> ErrorScreen(albumsDetailedInfo.errorMessage)
        Response.Loading -> {
            LoadingScreen(
                modifier = Modifier.size(130.dp),
                strokeWidth = 20.dp,
                MaterialTheme.colorScheme.primary
            )
        }
        is Response.Success<List<AlbumDetailedEntity>> -> {
            Carrousel(albumsDetailedInfo.response ?: emptyList(), onItemClick)
        }
    }
}