package com.poc.postitapp.presenter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.poc.persistence.domain.entities.PostItEntity
import com.poc.postitapp.presenter.screens.listeners.ListenViewState
import com.poc.postitapp.presenter.viewintents.ViewStates
import com.poc.postitapp.presenter.viewintents.crudintent.CRUDIntents
import com.poc.postitapp.presenter.viewintents.crudintent.ManageDataViewModel

@Composable
fun PostItScreen(manageDataViewModel: ManageDataViewModel, onClickDetail: (PostItEntity) -> Unit) {
    manageDataViewModel.handleIntent(CRUDIntents.GetAllData)
    val postItsData = manageDataViewModel.mainState.collectAsState()
    val listener  = object : ListenViewState {
        @Composable
        override fun OnSuccess(postIts: List<PostItEntity>) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
                    items(items = postIts, itemContent = {
                        Text(text = it.title ?: "")
                        Text(text = it.description ?: "")
                    })
                }
            }
        }

        @Composable
        override fun OnError(message: String) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "Error: $message")
            }
        }

        @Composable
        override fun OnLoading() {
            Text(text = "Loading...")
        }

    }

    ManageStateValue(postItsData.value, listener)
}

@Composable
fun ManageStateValue(postItsData: ViewStates, listener: ListenViewState) {
    when (postItsData) {
        is ViewStates.Error -> {
            listener.OnError(postItsData.errorMessage ?: "Error")
        }
        is ViewStates.LoadData -> {
            listener.OnSuccess(postIts = postItsData.data ?: emptyList())
        }
        ViewStates.Loading -> {
            listener.OnLoading()
        }
    }
}