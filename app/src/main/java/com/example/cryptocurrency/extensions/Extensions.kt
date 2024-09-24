package com.example.cryptocurrency.extensions

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cryptocurrency.MainActivity
import com.example.cryptocurrency.databinding.CategoryTabViewBinding
import com.example.cryptocurrency.domain.entities.CategoriesEntity
import com.example.cryptocurrency.domain.entities.LogMessage
import com.example.cryptocurrency.utils.ScreenManager
import com.google.android.material.tabs.TabLayout

fun String.changeScreen(activity: MainActivity) {
    val transaction = activity.supportFragmentManager.beginTransaction()
    val fragment = ScreenManager.getFragment(this)
    transaction.setReorderingAllowed(true)
    transaction.replace(activity.binding.screensCanvas.id, fragment)
    transaction.commit()
}

fun String.toastLong(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}

fun String.toastShort(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun TabLayout.setCustomCategories(list: List<CategoriesEntity>, context: Context) {
    if (list.size <= 4) {
        tabGravity = TabLayout.GRAVITY_FILL
        tabMode = TabLayout.MODE_FIXED
    } else {
        tabGravity = TabLayout.GRAVITY_CENTER
        tabMode = TabLayout.MODE_SCROLLABLE
    }



    list.forEach {
        val view = CategoryTabViewBinding.inflate(LayoutInflater.from(context), this, false)
        view.categoryName.text = it.name

        val tab = newTab()
            .setCustomView(view.root)
            .setTag(it)

        addTab(tab, it.isSelected)
    }

    requestLayout()


}

fun LogMessage.e() {
    Log.e(tag ,message)
}
fun LogMessage.i() {
    Log.i(tag ,message)
}
fun LogMessage.d() {
    Log.d(tag ,message)
}