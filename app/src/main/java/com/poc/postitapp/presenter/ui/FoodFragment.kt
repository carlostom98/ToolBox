package com.poc.postitapp.presenter.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.poc.postitapp.databinding.FragmentScreen2Binding

import com.poc.postitapp.presenter.MainActivity
import com.poc.postitapp.presenter.viewintents.ViewStates
import com.poc.postitapp.presenter.viewintents.imagesintent.GetImagesViewModel
import com.poc.postitapp.presenter.viewintents.imagesintent.ImageIntents
import com.poc.postitapp.utils.URLS
import kotlinx.coroutines.launch


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

        with(binding) {
            composeView.setContent {
                val coroutineScope = rememberCoroutineScope()
                var currentImage: Bitmap? by remember { mutableStateOf(null) }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                    contentAlignment = Alignment.Center
                    ){
                    Column() {
                        currentImage?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "Image",
                                modifier = Modifier.size(400.dp)
                            )
                        }
                        Button(onClick = {
                            if (pairClick) {
                                coroutineScope.launch {
                                    activity.viewModelGetImages.userIntent.send(ImageIntents.GetLeakedImage(currentImage!!))
                                }
                            } else {
                                coroutineScope.launch {
                                    activity.viewModelGetImages.userIntent.send(ImageIntents.GetOriginalImage(URLS.URL_IMAGE_BEACH))
                                }
                            }
                            pairClick = !pairClick
                        }, modifier = Modifier.wrapContentSize()) {
                            Text(text = "Change Image filter")
                        }
                    }

                    updateViews(
                        getImagesViewModel = activity.viewModelGetImages,
                        loadingComplete = {
                            loadingComplete()
                            currentImage = it
                        } ,
                        imageLoading = {
                            imageLoading()
                        },
                        errorLoading = {
                            Log.e("TAG", it)
                        })

                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun imageLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.composeView.visibility = View.GONE
    }

    private fun loadingComplete() {
        binding.composeView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }
}

@Composable
fun updateViews(getImagesViewModel: GetImagesViewModel,
                loadingComplete: (Bitmap) -> Unit,
                imageLoading: () -> Unit,
                errorLoading: (messageError: String) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        getImagesViewModel.mainState.collect(){ viewState ->
            when (viewState) {
                is ViewStates.Error -> errorLoading(viewState.errorMessage ?: "Error")
                ViewStates.Idle -> Log.d("IMAGE_LOADING", "IDLE")
                is ViewStates.LoadData<*> -> {
                    if (viewState.data is Bitmap) {
                        loadingComplete(viewState.data)
                    } else {
                        Log.d("IMAGE_LOADING", "Value: ${viewState.data as String}")
                    }
                }
                ViewStates.Loading -> imageLoading()
            }
        }
    }
}