package com.poc.postitapp.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.poc.persistence.domain.entities.PostItEntity
import com.poc.postitapp.presenter.navigation.custom.CustomNavTypePostIt
import com.poc.postitapp.presenter.screens.CreatePostItScreen
import com.poc.postitapp.presenter.screens.DetailScreen
import com.poc.postitapp.presenter.screens.PostItScreen
import com.poc.postitapp.presenter.viewintents.crudintent.ManageDataViewModel
import kotlin.reflect.typeOf

@Composable
fun NavigationStack(manageDataViewModel: ManageDataViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = PostItScreen) {
        composable<PostItScreen> {
            PostItScreen(manageDataViewModel) { postItEntity ->
                navController.navigate(DetailScreen(postItEntity))
            }
        }

        composable<DetailScreen>(
            typeMap = mapOf(
                typeOf<PostItEntity>() to CustomNavTypePostIt.PostItType
            )
        ) { backStackEntry ->
            val detail = backStackEntry.toRoute<DetailScreen>()
            DetailScreen(manageDataViewModel, detail.postItEntity)
        }

        composable<CreatePostItScreen> {
            CreatePostItScreen {

            }
        }
    }
}

