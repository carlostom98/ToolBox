package com.poc.postitapp.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.poc.postitapp.presenter.viewintents.crudintent.ManageDataViewModel

@Composable
fun CreatePostItScreen(manageDataViewModel: ManageDataViewModel) {
    var textTitle by rememberSaveable {
        mutableStateOf("")
    }

    var textDescription by rememberSaveable {
        mutableStateOf("")
    }

    var isExpandedColor = remember {
        mutableStateOf(false)
    }

    var isExpandedUrgencyLevel = remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)
        .wrapContentSize(Alignment.TopCenter)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(35.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(value = textTitle, onValueChange = {
                textTitle = it
            }, label = { Text(text = "Title") }
            )

            OutlinedTextField(value = textDescription, onValueChange = {
                textDescription = it
            }, label = { Text(text = "Description") }
            )

            Row {
                Text(text = "Pick a Color: ")
            }

            Row {
                Text(text = "Pick Urgency Level: ")
            }
        }
    }
}






