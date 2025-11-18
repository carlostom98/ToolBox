package com.poc.toolboxapp.presenter.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.poc.domain.entities.PhotosEntity
import com.poc.toolboxapp.presenter.navigation.custom.CustomNavType
import com.poc.toolboxapp.presenter.screens.DetailScreen
import com.poc.toolboxapp.presenter.screens.HomeScreen
import kotlin.reflect.typeOf

@Composable
fun NavigationStack(
    paddingValues: PaddingValues,
    context: Context = LocalContext.current
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreenDestiny) {
        composable<HomeScreenDestiny> {
            HomeScreen(hiltViewModel()) {
                navController.navigate(DetailScreenDestiny(it))
            }
        }

        composable<DetailScreenDestiny>(
            typeMap = mapOf(
                typeOf<PhotosEntity>() to CustomNavType.PhotosType
            )
        ) {
            val arguments = it.toRoute<DetailScreenDestiny>()
            DetailScreen(arguments.photosEntity)
        }
    }


}

