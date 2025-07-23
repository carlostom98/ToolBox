package com.poc.postitapp.presenter.screens.manageresult

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.poc.domain.entities.PostItEntity
import com.poc.postitapp.presenter.screens.LoadingScreen
import com.poc.postitapp.presenter.screens.PostItList
import com.poc.postitapp.presenter.screens.listeners.ListenViewState
import com.poc.viewmodel.viewintents.ViewStates
import com.poc.postitapp.utils.extensions.shortToast

@Composable
fun ManageStateValue(viewState: ViewStates, listener: ListenViewState) =
    when (viewState) {
        is ViewStates.Error -> {
            listener.OnError(viewState.errorMessage ?: "Error")
        }

        is ViewStates.LoadData -> {
            listener.OnSuccess(postIts = viewState.data.postItsList?: emptyList())
        }

        ViewStates.Loading -> {
            listener.OnLoading()
        }
    }

// State handler for Create PostItScreen
@Composable
fun listenerCreatePostItScreen(context: Context) = object : ListenViewState {
    @Composable
    override fun OnSuccess(postIts: List<PostItEntity>) {
        "Post It Created Successfully".shortToast(context)
    }

    @Composable
    override fun OnError(message: String) {
        message.shortToast(context)
    }

    @Composable
    override fun OnLoading() {
        "Save loading".shortToast(context)
    }

}

// State handler for PostItsScreen

@Composable
fun listenerPostItsScreen(
    context: Context,
    onClickDetail: (PostItEntity) -> Unit,
    onClickNewPostIt: () -> Unit,
    onDelete: (PostItEntity) -> Unit
    ) = object : ListenViewState {
    @Composable
    override fun OnSuccess(postIts: List<PostItEntity>) {
        PostItList(modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(10.dp), postIts = postIts, onClick = { postIt ->
            onClickDetail(postIt)
        }, onClickCreateNew = {
            onClickNewPostIt()
        }, onDelete = {
            onDelete(it)
        })
        "Data retrieved successfully".shortToast(context)
    }

    @Composable
    override fun OnError(message: String) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "Error: $message")
        }
    }

    @Composable
    override fun OnLoading() {
        LoadingScreen(
            modifier = Modifier.size(80.dp),
            strokeWidth = 20.dp,
            MaterialTheme.colorScheme.secondary
        )
    }
}