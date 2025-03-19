package com.poc.postitapp.presenter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.postitapp.domain.entities.PostItEntity
import com.poc.postitapp.presenter.viewintents.crudintent.ManageDataViewModel

@Composable
fun DetailScreen(manageDataViewModel: ManageDataViewModel, postItEntity: PostItEntity) {

    Box {
        Column {
            Text(text = postItEntity.title ?: "")
            Text(text = postItEntity.description ?: "")
        }
    }

}

