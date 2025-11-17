package com.poc.toolboxapp.presenter.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorScreen(error: String) {
    Text(error)
}