package com.poc.postitapp.presenter.navigation

sealed class GoTo(val route: String) {

    data object MainScreen : GoTo("MainScreen")
    data object DetailScreen : GoTo("DetailScreen")

}