package com.poc.postitapp.presenter.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.poc.postitapp.presenter.viewintents.crudintent.ManageDataViewModel

@Composable
fun CreatePostItScreen(manageDataViewModel: ManageDataViewModel) {
    LoadingScreen(modifier = Modifier.size(80.dp), strokeWidth = 20.dp, MaterialTheme.colors.primary)
}