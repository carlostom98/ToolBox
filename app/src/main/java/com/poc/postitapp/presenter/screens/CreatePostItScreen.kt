package com.poc.postitapp.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.persistence.domain.entities.PostItEntity
import com.poc.persistence.domain.entities.UrgencyLevel
import com.poc.postitapp.presenter.viewintents.crudintent.ManageDataViewModel

@Composable
fun CreatePostItScreen(onClickSave: (PostItEntity) -> Unit) {
    var postItEntity by remember {
        mutableStateOf(
            PostItEntity(
                title = "",
                description = "",
                color = 0xFFFFFF,
                urgencyLevel = UrgencyLevel.LOW
            )
        )
    }

    var onFocusedTitle by remember {
        mutableStateOf(false)
    }

    var onFocusedDescription by remember {
        mutableStateOf(false)
    }

    val outlinedSelectedColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.secondary,
        focusedTextColor = MaterialTheme.colorScheme.secondary,
        focusedLabelColor = MaterialTheme.colorScheme.secondary
    )

    val spacerDistance = 40.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(35.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Create your PostIt !!",
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1.5F),
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    fontStyle = FontStyle.Italic,
                    style = TextStyle(
                        color = Color.DarkGray
                    )
                )

                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Icon Title",
                    modifier = Modifier
                        .weight(1F)
                        .size(30.dp),
                    tint = Color.Green
                )
            }


            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(spacerDistance)
            )

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusedTitle = true
                    onFocusedDescription = false
                }, value = postItEntity.title ?: "", onValueChange = {
                postItEntity = postItEntity.copy(title = it)
            }, label = { Text(text = "Title") },
                colors = outlinedSelectedColors,
                textStyle = TextStyle(
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic
                )
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(spacerDistance)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = postItEntity.description ?: "",
                onValueChange = {
                    postItEntity = postItEntity.copy(description = it)
                },
                label = { Text(text = "Description") },
                colors = outlinedSelectedColors,
                textStyle = TextStyle(
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic
                )
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(spacerDistance)
            )

            DropDownMenuPicker(text = "Pick A Color")

            DropDownMenuPicker(text = "Pick An UrgencyLevel")

            Button(
                onClick = { onClickSave(postItEntity) },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondary),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Save PostIt",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

        }
    }
}

@Composable
fun DropDownMenuPicker(text: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp,
            style = TextStyle(
                color = Color.DarkGray
            )
        )
    }
}






