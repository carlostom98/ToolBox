package com.poc.postitapp.presenter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.poc.persistence.domain.entities.PostItEntity
import com.poc.postitapp.presenter.viewintents.crudintent.ManageDataViewModel

@Composable
fun DetailScreen(manageDataViewModel: ManageDataViewModel, postItEntity: PostItEntity) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = postItEntity.title ?: "1")
            Text(text = postItEntity.description ?: "2")
            Text(text = postItEntity.urgencyLevel?.name ?: "3")
        }
    }

}