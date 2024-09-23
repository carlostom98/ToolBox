package com.example.cryptocurrency

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrency.databinding.ActivityMainBinding
import com.example.cryptocurrency.domain.entities.CategoriesEntity
import com.example.cryptocurrency.extensions.setCustomCategories
import com.example.cryptocurrency.presenter.Screen2
import com.example.cryptocurrency.presenter.viewintents.UpdaterViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: UpdaterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = Screen2()
            transaction.setReorderingAllowed(true)
            transaction.replace(binding.screensCanvas.id, fragment)
            transaction.commit()
        }

        val listOfTabs: List<CategoriesEntity> = listOf(
            CategoriesEntity(isSelected = true, name = "Drink"),
            CategoriesEntity(name = "Food"),
            CategoriesEntity(name = "Meal"),
            CategoriesEntity(name = "Snack"),
            CategoriesEntity(name = "wsdfghjk"),
            CategoriesEntity(name = "asdfghjklnc"),
            CategoriesEntity(name = "lakajshha"),
            CategoriesEntity(name = "asdfghjmajaja"),
            CategoriesEntity(name = "daddadaassa"),
            CategoriesEntity(name = "papapapasksks")
        )


        with(binding) {
            tabLayout.setCustomCategories(listOfTabs, this@MainActivity)
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

                override fun onTabReselected(tab: TabLayout.Tab?) = Unit

            })

        }


    }


}


