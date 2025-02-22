package com.poc.postitapp.presenter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.poc.postitapp.databinding.ActivityMainBinding
import com.poc.postitapp.presenter.viewintents.getcountriesintent.GetCountriesFromRemoteViewModel
import com.poc.postitapp.presenter.viewintents.imagesintent.GetImagesViewModel
import com.poc.postitapp.presenter.viewintents.mainintents.UpdaterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    val viewModelGetImages: GetImagesViewModel by viewModels()

    val updaterViewModel: UpdaterViewModel by viewModels()

    val getCountriesViewModel: GetCountriesFromRemoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}


