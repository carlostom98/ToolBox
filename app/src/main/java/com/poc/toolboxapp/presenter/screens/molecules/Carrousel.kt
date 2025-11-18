package com.poc.toolboxapp.presenter.screens.molecules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.domain.entities.AlbumDetailedEntity
import com.poc.domain.entities.PhotosEntity
import com.poc.toolboxapp.utils.extensions.DpMeasureUtils
import com.poc.toolboxapp.utils.extensions.DpMeasureUtils.BIG_IMAGE_SIZE
import com.poc.toolboxapp.utils.extensions.DpMeasureUtils.IMAGE_SIZE
import com.poc.toolboxapp.utils.extensions.SpMeasureUtils

@Composable
fun Carrousel(albumsDetailed: List<AlbumDetailedEntity>, onItemClick: (PhotosEntity) -> Unit) {

    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(albumsDetailed) { album ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    album.title, style = TextStyle(
                        fontSize = SpMeasureUtils.TITLE_TEXT_SIZE.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp,
                        color = Color.Black
                    )
                )
            }

            val listState = rememberLazyListState()
            val mid by remember {
                derivedStateOf {
                    val visibleItems = listState.layoutInfo.visibleItemsInfo
                    if (visibleItems.isEmpty()) {
                        null
                    } else {
                        visibleItems.getOrNull(visibleItems.size / 2)?.index
                    }
                }
            }
            LazyRow(state = listState) {
                itemsIndexed(album.photos) { index, photos ->
                    val isMid = index == mid
                    val size = if (isMid) BIG_IMAGE_SIZE else IMAGE_SIZE
                    PhotosCard(
                        modifier = Modifier
                            .padding(DpMeasureUtils.SMALL_SPACER.dp)
                            .wrapContentHeight()
                            .clickable {
                                onItemClick(photos)
                            },
                        photo = photos,
                        imageModifier = Modifier
                            .size(size.dp)
                            .clip(CircleShape)
                    )
                }
            }
            HorizontalDivider(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}