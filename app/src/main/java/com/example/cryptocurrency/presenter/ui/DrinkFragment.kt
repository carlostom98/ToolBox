package com.example.cryptocurrency.presenter.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency.data.persistance.RemotePersistence
import com.example.cryptocurrency.data.persistance.RemotePersistence.countriesValue
import com.example.cryptocurrency.presenter.MainActivity
import com.example.cryptocurrency.databinding.FragmentScreen1Binding
import com.example.cryptocurrency.domain.entities.SuperHeroData
import com.example.cryptocurrency.domain.entities.SuperheroHideouts
import com.example.cryptocurrency.presenter.recyclerviewres.SuperHeroAdapter
import com.example.cryptocurrency.presenter.viewintents.mainintents.MainIntent
import com.example.cryptocurrency.presenter.viewintents.mainintents.UpdaterViewModel
import com.example.cryptocurrency.presenter.viewintents.ViewStates
import kotlinx.coroutines.launch


@Suppress("UNCHECKED_CAST")
class DrinkFragment : Fragment() {

    private var _binding: FragmentScreen1Binding? = null
    private val binding get() = _binding!!
    private var superHeroAdapter: SuperHeroAdapter? = null
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
                        viewModel.userIntent.send(MainIntent.FetchTodoTask)
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