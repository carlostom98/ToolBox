package com.poc.postitapp.presenter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.poc.persistence.domain.entities.PostItEntity
import com.poc.persistence.domain.entities.UrgencyLevel
import com.poc.postitapp.presenter.viewintents.crudintent.CRUDIntents
import com.poc.postitapp.presenter.viewintents.crudintent.ManageDataViewModel

@Composable
fun postItScreen(manageDataViewModel: ManageDataViewModel) {
    var textFieldOne by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Row(modifier = Modifier.wrapContentHeight()) {
            Button(onClick = { manageDataViewModel.handleIntent(
                CRUDIntents.UpsertPostIt(
                    PostItEntity(
                        title = textFieldOne,
                        urgencyLevel = UrgencyLevel.HIGH,
                        color = "#FFFFFF",
                        description = "Hello this is my first PostIt"
                    )
                )
            )}) {
                Text(text = "Click me")
            }

            TextField(value = textFieldOne, onValueChange = {
                textFieldOne = it
            })
        }
    }
}