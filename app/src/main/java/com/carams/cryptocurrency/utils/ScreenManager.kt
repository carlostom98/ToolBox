package com.carams.cryptocurrency.utils

import androidx.fragment.app.Fragment
import com.carams.cryptocurrency.presenter.ui.DrinkFragment
import com.carams.cryptocurrency.presenter.ui.FoodFragment
import com.carams.cryptocurrency.presenter.ui.MealFragment

object ScreenManager {
    fun getFragment(key: String) = listOfFragments[key]!!

    private val listOfFragments: Map<String, Fragment> = mapOf(
        "Drink" to DrinkFragment(),
        "Food" to FoodFragment(),
        "Meal" to MealFragment(),
        "Snack" to MealFragment()
    )
}