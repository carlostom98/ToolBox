package com.poc.postitapp.utils.extensions

import androidx.fragment.app.Fragment
import com.poc.postitapp.presenter.MainActivity

fun Fragment.changeScreen(activity: MainActivity) {
    val transaction = activity.supportFragmentManager.beginTransaction()
    transaction.setReorderingAllowed(true)
    transaction.replace(activity.binding.screensCanvas.id, this)
    transaction.commit()
}