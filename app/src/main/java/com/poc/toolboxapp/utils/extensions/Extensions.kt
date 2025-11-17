package com.poc.toolboxapp.utils.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.poc.toolboxapp.presenter.MainActivity

fun Fragment.changeScreen(activity: MainActivity) {
    val transaction = activity.supportFragmentManager.beginTransaction()
    transaction.setReorderingAllowed(true)
    transaction.replace(activity.binding.screensCanvas.id, this)
    transaction.commit()
}

fun String.shortToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.longToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}