package com.poc.postitapp.presenter.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.poc.domain.entities.PostItEntity
import com.poc.postitapp.presenter.screens.manageresult.ManageStateValue
import com.poc.postitapp.presenter.screens.manageresult.listenerPostItsScreen
import com.poc.viewmodel.viewintents.ViewStates
import com.poc.viewmodel.viewintents.crudintent.CRUDIntents
import com.poc.viewmodel.viewintents.crudintent.ManageDataViewModel

@Composable
fun PostItScreen(
    manageDataViewModel: ManageDataViewModel,
    viewState: State<ViewStates>,
    onClickDetail: (PostItEntity) -> Unit,
    onClickNewPostIt: () -> Unit,
    onDelete: (PostItEntity) -> Unit,
    context: Context
) {

    manageDataViewModel.handleIntent(CRUDIntents.GetAllData)

    ManageStateValue(
        viewState.value,
        listenerPostItsScreen(
            context = context,
            onClickDetail = onClickDetail,
            onClickNewPostIt = onClickNewPostIt,
            onDelete
        )
    )
}


