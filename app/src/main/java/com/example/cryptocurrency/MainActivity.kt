package com.example.cryptocurrency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrency.databinding.ActivityMainBinding
import com.example.cryptocurrency.domain.entities.CategoriesEntity
import com.example.cryptocurrency.extensions.setCustomCategories
import com.example.cryptocurrency.extensions.toastShort
import com.example.cryptocurrency.presenter.Screen1
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = Screen1()
            transaction.setReorderingAllowed(true)
            transaction.replace(binding.screensCanvas.id, fragment)
            transaction.commit()

        }

        val listOfTabs: List<CategoriesEntity> = listOf(
            CategoriesEntity(false, "Drink"),
            CategoriesEntity(true, "Food"),
            CategoriesEntity(true, "Meal"),
            CategoriesEntity(true, "Snack"),
            CategoriesEntity(false, "Beverages"),
        )


        with(binding) {
            tabLayout.setCustomCategories(listOfTabs, this@MainActivity)
        }



    }



}


