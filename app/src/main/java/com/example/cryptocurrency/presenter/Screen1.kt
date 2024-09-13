package com.example.cryptocurrency.presenter

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency.MainActivity
import com.example.cryptocurrency.databinding.FragmentScreen1Binding
import com.example.cryptocurrency.domain.entities.SuperHeroData
import com.example.cryptocurrency.presenter.recyclerviewres.SuperHeroAdapter
import com.example.cryptocurrency.presenter.viewintents.MainIntent
import com.example.cryptocurrency.presenter.viewintents.UpdaterViewModel
import com.example.cryptocurrency.presenter.viewintents.ViewStates
import kotlinx.coroutines.launch


@Suppress("UNCHECKED_CAST")
class Screen1 : Fragment() {

    private var _binding: FragmentScreen1Binding? = null
    private val binding get() = _binding!!
    private lateinit var superHeroAdapter: SuperHeroAdapter
    private val viewModel: UpdaterViewModel by activityViewModels()



    private lateinit var activity: Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity = getActivity() as MainActivity
        _binding = FragmentScreen1Binding.inflate(inflater, container, false)
        lifecycleScope.launch {
            viewModel.mainState.collect { viewState ->
                when(viewState) {
                    is ViewStates.Error -> Log.e("View", "${viewState.errorMessage}")
                    ViewStates.Idle -> Log.d("View", "IDLE")
                    is ViewStates.LoadSuperheroes<*> -> {
                        superHeroAdapter.updateList(viewState.superheroes as List<SuperHeroData>)
                    }
                    ViewStates.Loading -> Log.d("View", "IDLE")
                }
            }
        }

        superHeroAdapter = SuperHeroAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (::activity.isInitialized && ::superHeroAdapter.isInitialized) {
            with(binding) {
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = superHeroAdapter
                }

                addItems.setOnClickListener {
                    lifecycleScope.launch {
                        viewModel.userIntent.send(MainIntent.FetchTodoTask)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private var superheroes = listOf(
            SuperHeroData("kotlinman", "1"),
            SuperHeroData("javaman", "2"),
            SuperHeroData("superman", "3"),
            SuperHeroData("androidman", "4"),
            SuperHeroData("androidman", "5"),
            SuperHeroData("androidman", "6"),
            SuperHeroData("androidman", "7"),
            SuperHeroData("androidman", "8"),
            SuperHeroData("androidman", "9"),
            SuperHeroData("androidman", "10"),
            SuperHeroData("androidman", "11"),
            SuperHeroData("androidman", "12"),
            SuperHeroData("androidman", "13"),
            SuperHeroData("androidman", "14"),
            SuperHeroData("androidman", "15"),
            SuperHeroData("androidman", "16"),
            SuperHeroData("androidman", "17")

        )
        private var superheroes2 = listOf(
            SuperHeroData("2", "6"),
            SuperHeroData("otro", "7"),
            SuperHeroData("quinto", "30"),
            SuperHeroData("lenin", "41"),
            SuperHeroData("neo", "42"),
        )
    }
}