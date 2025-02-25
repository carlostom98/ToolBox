package com.poc.postitapp.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.poc.postitapp.databinding.ActivityMainBinding
import com.poc.postitapp.utils.extensions.changeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            EntryFragment().changeScreen(this)
        }
    }
}


