package com.poc.postitapp.presenter.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.poc.postitapp.presenter.screens.LoadingScreen
import com.poc.viewmodel.viewintents.crudintent.ManageDataViewModel

@Composable
fun NavigationStack(
    manageDataViewModel: ManageDataViewModel,
    paddingValues: PaddingValues,
    context: Context = LocalContext.current
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            LoadingScreen(
                modifier = Modifier.fillMaxSize(),
                strokeWidth = 50.dp,
                MaterialTheme.colorScheme.primary
            )
        }
    }


}

