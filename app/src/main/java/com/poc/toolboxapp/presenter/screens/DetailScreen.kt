package com.poc.toolboxapp.presenter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.poc.domain.entities.PhotosEntity
import com.poc.toolboxapp.presenter.screens.molecules.DetailCard
import com.poc.toolboxapp.utils.extensions.DpMeasureUtils

@Composable
fun DetailScreen(photosEntity: PhotosEntity) {
    Box(modifier = Modifier.padding(DpMeasureUtils.SMALL_PADDING.dp).fillMaxSize()) {
        DetailCard(modifier = Modifier.fillMaxWidth().wrapContentHeight(), photosEntity = photosEntity)
    }
}