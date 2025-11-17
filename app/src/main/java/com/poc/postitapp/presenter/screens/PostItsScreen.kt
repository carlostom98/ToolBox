package com.poc.postitapp.presenter.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.poc.postitapp.presenter.screens.manageresult.ManageStateValue
import com.poc.postitapp.presenter.screens.manageresult.listenerPostItsScreen
import com.poc.viewmodel.viewintents.ViewStates
import com.poc.viewmodel.viewintents.crudintent.CRUDIntents
import com.poc.viewmodel.viewintents.crudintent.ManageDataViewModel

@Composable
fun PostItScreen(
    viewState: State<ViewStates>,
    onClickDetail: (PostItEntity) -> Unit,
    onClickNewPostIt: () -> Unit,
    onDelete: (PostItEntity) -> Unit,
    onSortedTypeSelected: (SortedBy) -> Unit,
    context: Context
) {

    ManageStateValue(
        viewState.value,
        listenerPostItsScreen(
            context = context,
            onClickDetail = onClickDetail,
            onClickNewPostIt = onClickNewPostIt,
            onDelete,
            onSortedTypeSelected = onSortedTypeSelected
        )
    )
}


