package com.poc.toolboxapp.presenter.navigation

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.poc.toolboxapp.presenter.screens.HomeScreen
import com.poc.toolboxapp.utils.extensions.DpMeasureUtils

@Composable
fun NavigationStack(
    paddingValues: PaddingValues,
    context: Context = LocalContext.current
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            HomeScreen(hiltViewModel())
        }
    }


}

