package com.example.cryptocurrency.presenter.viewintents

import com.example.cryptocurrency.domain.entities.SuperHeroData

sealed class ViewStates  () {
    object Idle: ViewStates()
    object Loading: ViewStates()
    data class LoadSuperheroes <T> (val superheroes: T): ViewStates()
    data class Error(val errorMessage: String?) : ViewStates()
}