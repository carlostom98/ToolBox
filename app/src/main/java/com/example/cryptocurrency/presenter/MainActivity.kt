package com.example.cryptocurrency.presenter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrency.databinding.ActivityMainBinding
import com.example.cryptocurrency.domain.entities.CategoriesEntity
import com.example.cryptocurrency.extensions.changeScreen
import com.example.cryptocurrency.extensions.setCustomCategories
import com.example.cryptocurrency.presenter.viewintents.getcountriesintent.GetCountriesFromRemoteViewModel
import com.example.cryptocurrency.presenter.viewintents.imagesintent.GetImagesViewModel
import com.example.cryptocurrency.presenter.viewintents.mainintents.UpdaterViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModelUpdater: UpdaterViewModel by viewModels()
    val getImagesViewModel: GetImagesViewModel by viewModels()
    val getCountriesViewModel: GetCountriesFromRemoteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            "Food".changeScreen(this)
        }

        val listOfTabs: List<CategoriesEntity> = listOf(
            CategoriesEntity(isSelected = true, name = "Drink"),
            CategoriesEntity(name = "Food"),
            CategoriesEntity(name = "Meal"),
            CategoriesEntity(name = "Snack"),
        )


        with(binding) {
            tabLayout.setCustomCategories(listOfTabs, this@MainActivity)
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let {
                        val name = (it.tag as CategoriesEntity).name
                        name.changeScreen(this@MainActivity)
                    } ?: Toast.makeText(this@MainActivity, "Tab cannot be null", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

                override fun onTabReselected(tab: TabLayout.Tab?) = Unit

            })

        }
    }


}


