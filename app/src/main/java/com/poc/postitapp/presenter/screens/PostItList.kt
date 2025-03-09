package com.poc.postitapp.presenter.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.persistence.domain.entities.PostItEntity
import kotlinx.coroutines.delay

@Composable
fun PostItList(
    modifier: Modifier,
    postIts: List<PostItEntity>,
    onClick: (PostItEntity) -> Unit,
    onDelete: (PostItEntity) -> Unit,
    onClickCreateNew: () -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            items(items = postIts, itemContent = { item ->
                SwipeToDeleteContainer(onDelete = {
                    onDelete(item)
                }) {
                    ListRow(postIt = item) {
                        onClick(item)
                    }
                }
            })
        }
        FloatButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(70.dp)
                .offset(x = (-20).dp, y = (-20).dp)
        ) {
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
fun SwipeToDeleteContainer(
    onDelete: () -> Unit,
    animationDuration: Int = 1000,
    content: @Composable () -> Unit
) {

    var isRemoved by remember {
        mutableStateOf(false)
    }

    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    AnimatedVisibility(
        visible = !isRemoved, exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        )
    ) {

        SwipeToDismissBox(state = state, backgroundContent = {
            DeleteBackGround(swipeDismissState = state)
        }, content = {
            content()
        }, enableDismissFromStartToEnd = false)
    }
    
    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete()
            isRemoved = false
        }
    }

}

@Composable
fun DeleteBackGround(swipeDismissState: SwipeToDismissBoxState) {
    val color = when (swipeDismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> MaterialTheme.colorScheme.secondary
        SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.secondary
        SwipeToDismissBoxValue.Settled -> Color.Transparent
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete item",
            tint = Color.Black
        )

    }
}


@Composable
fun ListRow(postIt: PostItEntity, onClick: (PostItEntity) -> Unit) {

    Card(
        onClick = { onClick(postIt) },
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = postIt.color?.let { Color(it) } ?: Color.White
        )
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
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}