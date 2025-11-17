package com.poc.toolboxapp.presenter.navigation

import kotlinx.serialization.Serializable


@Serializable
data object HomeScreen

@Serializable
data class DetailScreen(val message: String)
