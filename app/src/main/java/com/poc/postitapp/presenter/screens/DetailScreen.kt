package com.poc.postitapp.presenter.screens

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.domain.entities.PostItEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    postItEntity: PostItEntity,
    saveData: (postIt: PostItEntity) -> Unit,
    context: Context = LocalContext.current
) {

    var isEditing by remember {
        mutableStateOf(false)
    }

    var postItEntityMutable by remember {
        mutableStateOf(postItEntity)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)
                .fillMaxWidth()
                .height(300.dp)
                .combinedClickable(
                    onLongClick = {
                        isEditing = true
                    },
                    onClick = {
                        if (isEditing) saveData(postItEntityMutable)
                        isEditing = false
                    }
                ),
            colors = CardDefaults.cardColors(
                containerColor = postItEntityMutable.color?.let { Color(it) } ?: Color.White
            )
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    ShowData(postItEntityMutable, isEditing, onTitleChange = {
                        postItEntityMutable = postItEntityMutable.copy(title = it)
                    }, onDescriptionChange = {
                        postItEntityMutable = postItEntityMutable.copy(description = it)
                    })
                }
            }
        }
    }

}

@Composable
fun ShowData(postItEntityMutable: PostItEntity, isEditing: Boolean, onTitleChange: (String) -> Unit, onDescriptionChange: (String) -> Unit) {

    val outlinedSelectedColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.secondary,
        focusedTextColor = MaterialTheme.colorScheme.secondary,
        focusedLabelColor = MaterialTheme.colorScheme.secondary
    )

    val mainTextWidth = 250.dp
    val textSize = 40.sp

    val defaultMainTextStyle = TextStyle(
        color = Color.Black,
        fontSize = textSize,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold
    )

    Column {
        Row {
            Text(
                text = "Title:", style = defaultMainTextStyle,
                modifier = Modifier.width(mainTextWidth)
            )
            if (isEditing) {
                Column {
                    OutlinedTextField(modifier = Modifier
                        .fillMaxWidth()
                        , value = postItEntityMutable.title ?: "", onValueChange = {
                            onTitleChange(it)
                        }, label = { Text(text = "Title") },
                        colors = outlinedSelectedColors,
                        textStyle = defaultMainTextStyle
                    )
                }
            } else {
                Text(
                    text = postItEntityMutable.title ?: "", style = TextStyle(
                        color = Color.Black,
                        fontSize = textSize,
                        fontStyle = FontStyle.Italic
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Text(
                text = "Description:", style = defaultMainTextStyle,
                modifier = Modifier.width(mainTextWidth)
            )

            if (isEditing)
            {
                Column {
                    OutlinedTextField(modifier = Modifier
                        .fillMaxWidth()
                        , value = postItEntityMutable.description ?: "", onValueChange = {
                            onDescriptionChange(it)
                        }, label = { Text(text = "Description") },
                        colors = outlinedSelectedColors,
                        textStyle = defaultMainTextStyle
                    )
                }
            } else {
                Text(
                    text = postItEntityMutable.description ?: "", style = TextStyle(
                        color = Color.Black,
                        fontSize = textSize
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

