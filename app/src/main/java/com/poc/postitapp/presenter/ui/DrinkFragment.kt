package com.poc.postitapp.presenter.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.poc.postitapp.databinding.FragmentScreen1Binding
import com.poc.postitapp.domain.entities.SuperHeroData
import com.poc.postitapp.domain.entities.SuperheroHideouts
import com.poc.postitapp.presenter.MainActivity
import com.poc.postitapp.presenter.recyclerviewres.SuperHeroAdapter
import com.poc.postitapp.presenter.viewintents.ViewStates
import com.poc.postitapp.presenter.viewintents.mainintents.MainIntent
import kotlinx.coroutines.launch


@Suppress("UNCHECKED_CAST")
class DrinkFragment : Fragment() {

    private var _binding: FragmentScreen1Binding? = null
    private val binding get() = _binding!!
    private var superHeroAdapter: SuperHeroAdapter? = null

    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity = getActivity() as MainActivity
        _binding = FragmentScreen1Binding.inflate(inflater, container, false)
        lifecycleScope.launch {
            activity.updaterViewModel.mainState.collect { viewState ->
                when (viewState) {
                    is ViewStates.Error -> Log.e("View", "${viewState.errorMessage}")
                    ViewStates.Idle -> Log.d("View", "IDLE")
                    is ViewStates.LoadData<*> -> {
                        val finalList = viewState.data as List<SuperHeroData> + superheroesHideouts
                        superHeroAdapter?.updateList(finalList) ?: Toast.makeText(
                            activity,
                            "The adapter is null",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    ViewStates.Loading -> Log.d("View", "IDLE")
                }
            }
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        superHeroAdapter = SuperHeroAdapter()
        if (::activity.isInitialized && superHeroAdapter != null) {
            with(binding) {
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = superHeroAdapter
                }

                addItems.setOnClickListener {
                    lifecycleScope.launch {
                        activity.updaterViewModel.userIntent.send(MainIntent.FetchTodoTask)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        superHeroAdapter = null
        super.onDestroyView()
    }

    companion object {
        private var superheroesHideouts = listOf(
            SuperheroHideouts("kotlinmanHideout", "1"),
            SuperheroHideouts("Baticueva", "2"),
            SuperheroHideouts("TorreStark", "3"),
            SuperheroHideouts("Avengers Building", "4"),
            SuperheroHideouts("Kamehouse", "5"),
            SuperheroHideouts("SuperHouse", "6"),


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