package com.poc.postitapp.presenter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.persistence.domain.entities.PostItEntity

@Composable
fun PostItList(modifier: Modifier, postIts: List<PostItEntity>, onClick: (PostItEntity) -> Unit, onClickCreateNew: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.Center) {
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            items(items = postIts, itemContent = {
                ListRow(postIt = it, onClick)
            })
        }
        FloatButton(modifier = Modifier.align(Alignment.BottomEnd).size(70.dp).offset(x = (-20).dp, y = (-20).dp)) {
            onClickCreateNew()
        }
    }
}

@Composable
fun FloatButton(modifier: Modifier, onClick: () -> Unit) {
    FloatingActionButton(modifier = modifier, onClick = { onClick() }) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}


@Composable
fun ListRow(postIt: PostItEntity, onClick: (PostItEntity) -> Unit) {

    Card(
        onClick = { },
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = postIt.title ?: "", style = TextStyle(
                        color = Color.Black,
                        fontSize = 25.sp,
                        fontStyle = FontStyle.Italic,
                    )
                )
                Text(
                    text = postIt.description ?: "", style = TextStyle(
                        color = Color(postIt.color ?: 0xFFFFFF),
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}