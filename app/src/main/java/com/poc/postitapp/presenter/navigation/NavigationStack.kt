package com.poc.postitapp.presenter.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.poc.domain.entities.PostItEntity
import com.poc.postitapp.presenter.navigation.custom.CustomNavTypePostIt
import com.poc.postitapp.presenter.screens.CreatePostItScreen
import com.poc.postitapp.presenter.screens.DetailScreen
import com.poc.postitapp.presenter.screens.PostItScreen
import com.poc.viewmodel.viewintents.crudintent.CRUDIntents
import com.poc.viewmodel.viewintents.crudintent.ManageDataViewModel
import com.poc.postitapp.utils.extensions.shortToast
import com.poc.viewmodel.viewintents.createoeditintent.CreateOrEditPostItViewModel
import kotlin.reflect.typeOf

@Composable
fun NavigationStack(
    manageDataViewModel: ManageDataViewModel,
    paddingValues: PaddingValues,
    context: Context = LocalContext.current
) {
    val navController = rememberNavController()
    val mainState = manageDataViewModel.mainState.collectAsState()


    NavHost(navController = navController, startDestination = PostItScreen) {
        composable<PostItScreen> {
            PostItScreen(
                context = context,
                viewState = mainState,
                onClickDetail = { postItEntity ->
                    navController.navigate(DetailScreen(postItEntity))
                },
                onClickNewPostIt = {
                    navController.navigate(CreatePostItScreen)
                },
                onDelete = { postIt ->
                    manageDataViewModel.handleIntent(CRUDIntents.DeletePostIt(postIt))
                }, onSortedTypeSelected = {
                    when (it) {
                        "Option 1" -> manageDataViewModel.handleIntent(CRUDIntents.GetAllData)
                        "Option 2" -> manageDataViewModel.handleIntent(CRUDIntents.GetAllDataSorted)
                    }
                })
        }

        composable<DetailScreen>(
            typeMap = mapOf(
                typeOf<PostItEntity>() to CustomNavTypePostIt.PostItType
            )
        ) { backStackEntry ->
            val detail = backStackEntry.toRoute<DetailScreen>()
            DetailScreen(detail.postItEntity, saveData = {
                manageDataViewModel.handleIntent(CRUDIntents.UpsertPostIt(it))
            })
        }

        composable<CreatePostItScreen> {
            CreatePostItScreen(createOrEditPostItViewModel = hiltViewModel<CreateOrEditPostItViewModel>(),mainState, context) { postItEntity ->
                with(postItEntity) {
                    if (title != null && description != null && color != null && urgencyLevel != null) {
                        manageDataViewModel.handleIntent(CRUDIntents.UpsertPostIt(postItEntity))
                    } else {
                        "Please fill all the requirements".shortToast(context)
                    }
                }
            }
        }
    }
}

