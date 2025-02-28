package com.poc.postitapp.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.poc.postitapp.databinding.FragmentEntryBinding
import com.poc.postitapp.presenter.navigation.NavigationStack
import com.poc.postitapp.presenter.screens.PostItScreen

class EntryFragment : Fragment() {

    private var _binding: FragmentEntryBinding? = null
    private val binding get() = _binding!!

    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity = getActivity() as MainActivity
        _binding = FragmentEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            composeView.setContent {
                NavigationStack(manageDataViewModel = activity.manageDataViewModel)
            }
        }
    }
}