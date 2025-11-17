package com.poc.toolboxapp.presenter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.poc.viewmodel.viewintents.crudintent.ManageDataViewModel
import com.poc.toolboxapp.utils.extensions.changeScreen
import com.poc.toolboxapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val manageDataViewModel: ManageDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        enableEdgeToEdge()
        if (savedInstanceState == null) {
            EntryFragment().changeScreen(this)
        }
    }
}


