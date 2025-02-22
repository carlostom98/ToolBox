package com.poc.postitapp.utils.extensions

import com.poc.postitapp.presenter.MainActivity

fun String.changeScreen(activity: MainActivity) {
    val transaction = activity.supportFragmentManager.beginTransaction()
    val fragment = ScreenManager.getFragment(this)
    transaction.setReorderingAllowed(true)
    transaction.replace(activity.binding.screensCanvas.id, fragment)
    transaction.commit()
}