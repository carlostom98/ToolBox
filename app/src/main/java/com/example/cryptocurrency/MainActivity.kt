package com.example.cryptocurrency

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrency.databinding.ActivityMainBinding
import com.example.cryptocurrency.domain.entities.CategoriesEntity
import com.example.cryptocurrency.domain.entities.LogMessage
import com.example.cryptocurrency.extensions.d
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
            CategoriesEntity(isSelected = true, name = "Drink"),
            CategoriesEntity( name = "Food"),
            CategoriesEntity( name ="Meal"),
            CategoriesEntity( name ="Snack"),
            CategoriesEntity( name ="Beverages"),
        )


        with(binding) {
            tabLayout.setCustomCategories(listOfTabs, this@MainActivity)
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    LogMessage("TAB_TAG", tab?.tag.toString()).d()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

                override fun onTabReselected(tab: TabLayout.Tab?) = Unit

            })

        }



    }



}


