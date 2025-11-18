package com.poc.toolboxapp.presenter.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.poc.domain.entities.PhotosEntity

@Composable
fun DetailScreen(photosEntity: PhotosEntity) {
    Text(photosEntity.title)
}