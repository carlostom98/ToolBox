package com.poc.postitapp.utils

import androidx.fragment.app.Fragment
import com.poc.postitapp.presenter.ui.DrinkFragment
import com.poc.postitapp.presenter.ui.FoodFragment
import com.poc.postitapp.presenter.ui.MealFragment

object ScreenManager {
    fun getFragment(key: String) = listOfFragments[key]!!

    private val listOfFragments: Map<String, Fragment> = mapOf(
        "Drink" to DrinkFragment(),
        "Food" to FoodFragment(),
        "Meal" to MealFragment(),
        "Snack" to MealFragment()
    )
}