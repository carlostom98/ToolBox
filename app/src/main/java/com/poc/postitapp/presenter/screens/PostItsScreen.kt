package com.poc.postitapp.presenter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.poc.persistence.domain.entities.PostItEntity
import com.poc.postitapp.presenter.screens.listeners.ListenViewState
import com.poc.postitapp.presenter.viewintents.ViewStates
import com.poc.postitapp.presenter.viewintents.crudintent.CRUDIntents
import com.poc.postitapp.presenter.viewintents.crudintent.ManageDataViewModel

@Composable
fun PostItScreen(manageDataViewModel: ManageDataViewModel, onClickDetail: (PostItEntity) -> Unit, onClickNewPostIt: () -> Unit) {
    manageDataViewModel.handleIntent(CRUDIntents.GetAllData)
    val postItsData = manageDataViewModel.mainState.collectAsState()
    val listener  = object : ListenViewState {
        @Composable
        override fun OnSuccess(postIts: List<PostItEntity>) {
            PostItList(modifier = Modifier, postIts = postIts, onClick = { postIt ->
                onClickDetail(postIt)
            }, onClickCreateNew = {
                onClickNewPostIt()
            })
        }

        @Composable
        override fun OnError(message: String) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "Error: $message")
            }
        }

        @Composable
        override fun OnLoading() {
            LoadingScreen(modifier = Modifier.size(80.dp), strokeWidth = 20.dp, MaterialTheme.colorScheme.primary)
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