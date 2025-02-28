package com.poc.postitapp.presenter.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class GoTo(private val route: String, private val arguments: MutableList<NavArguments> ? = null) {

    val baseRoute = run {
        val complimentString = arguments?.joinToString("/") { "{${it.key}}" } ?: ""
        route + complimentString
    }
    val navArgs = arguments?.map {
        navArgument(it.key) { it.navType }
    }

    data object MainScreen : GoTo("MainScreen", mutableListOf(NavArguments.PostItEntity)) // PostItEntity is a
    data object DetailScreen : GoTo("DetailScreen")

}

enum class NavArguments(val key: String, val navType: NavType<*>) {
    PostItEntity("postItEntity", NavType.StringType)
}