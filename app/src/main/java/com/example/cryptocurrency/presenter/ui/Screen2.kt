package com.example.cryptocurrency.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cryptocurrency.databinding.FragmentScreen2Binding
import com.example.cryptocurrency.presenter.viewintents.imagesintent.GetImagesViewModel


class Screen2 : Fragment() {


    private val viewModel: GetImagesViewModel by activityViewModels()
    private lateinit var _binding: FragmentScreen2Binding
    private val binding: FragmentScreen2Binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

        }
    }
}