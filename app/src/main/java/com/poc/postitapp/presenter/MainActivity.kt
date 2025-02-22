package com.poc.postitapp.presenter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.poc.postitapp.databinding.ActivityMainBinding
import com.poc.postitapp.domain.entities.CategoriesEntity
import com.poc.postitapp.extensions.changeScreen
import com.poc.postitapp.extensions.setCustomCategories
import com.poc.postitapp.presenter.viewintents.getcountriesintent.GetCountriesFromRemoteViewModel
import com.poc.postitapp.presenter.viewintents.imagesintent.GetImagesViewModel
import com.poc.postitapp.presenter.viewintents.mainintents.UpdaterViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    val viewModelGetImages: GetImagesViewModel by viewModels()

    val updaterViewModel: UpdaterViewModel by viewModels()

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


