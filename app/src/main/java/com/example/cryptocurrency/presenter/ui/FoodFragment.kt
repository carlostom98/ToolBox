package com.example.cryptocurrency.presenter.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.cryptocurrency.databinding.FragmentScreen2Binding
import com.example.cryptocurrency.presenter.MainActivity
import com.example.cryptocurrency.presenter.viewintents.ViewStates
import com.example.cryptocurrency.presenter.viewintents.imagesintent.GetImagesViewModel
import com.example.cryptocurrency.presenter.viewintents.imagesintent.ImageIntents
import com.example.cryptocurrency.utils.URLS
import kotlinx.coroutines.launch
import javax.inject.Inject


class FoodFragment : Fragment() {

    private var _binding: FragmentScreen2Binding? = null
    private val binding: FragmentScreen2Binding get() = _binding!!

    private var currentImage: Bitmap? = null
    private var pairClick = true

    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity = getActivity() as MainActivity
        _binding = FragmentScreen2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lifecycleScope.launch {
            activity.viewModelGetImages.userIntent.send(ImageIntents.GetOriginalImage(URLS.URL_IMAGE_BEACH))
        }

        lifecycleScope.launch {
            activity.viewModelGetImages.mainState.collect() { viewState ->
                when (viewState) {
                    is ViewStates.Error -> Log.e("IMAGE_LOADING", "Something went wrong")
                    ViewStates.Idle -> Log.d("IMAGE_LOADING", "IDLE")
                    is ViewStates.LoadData<*> -> {
                        loadingComplete()
                        if (viewState.data is Bitmap) {
                            currentImage = viewState.data
                            binding.imageView.setImageBitmap(viewState.data)
                        } else {
                            Log.d("IMAGE_LOADING", "Value: ${viewState.data as String}")
                        }
                    }

                    ViewStates.Loading -> imageLoading()
                }
            }
        }

        with(binding) {
            actionButton.setOnClickListener {
                if (pairClick) {
                    lifecycleScope.launch {
                        activity.viewModelGetImages.userIntent.send(ImageIntents.GetLeakedImage(currentImage!!))
                    }
                } else {
                    lifecycleScope.launch {
                        activity.viewModelGetImages.userIntent.send(ImageIntents.GetOriginalImage(URLS.URL_IMAGE_BEACH))
                    }
                }
                pairClick = !pairClick
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun imageLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.mainScreen2Canvas.visibility = View.GONE
    }

    private fun loadingComplete() {
        binding.mainScreen2Canvas.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }
}