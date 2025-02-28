package com.poc.postitapp.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.poc.postitapp.presenter.screens.DetailScreen
import com.poc.postitapp.presenter.screens.PostItScreen
import com.poc.postitapp.presenter.viewintents.crudintent.ManageDataViewModel

@Composable
fun NavigationStack(manageDataViewModel: ManageDataViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = GoTo.MainScreen.baseRoute) {
        composable(route = GoTo.MainScreen.baseRoute) {
            PostItScreen(manageDataViewModel, navController)
        }

        composable(
            route = GoTo.DetailScreen.baseRoute,
            arguments = GoTo.DetailScreen.navArgs ?: emptyList()
        ) {
            DetailScreen(manageDataViewModel, navController)
        }
    }
}