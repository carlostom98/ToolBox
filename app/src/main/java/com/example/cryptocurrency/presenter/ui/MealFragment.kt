package com.example.cryptocurrency.presenter.ui

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency.databinding.FragmentScreen3Binding
import com.example.cryptocurrency.domain.entities.CountriesEntity
import com.example.cryptocurrency.presenter.MainActivity
import com.example.cryptocurrency.presenter.recyclerviewres.CountriesAdapter


class MealFragment : Fragment() {

    private var _binding: FragmentScreen3Binding? = null
    private val binding get() = _binding!!
    private var countriesAdapter: CountriesAdapter? = null

    private lateinit var activity: Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity = getActivity() as MainActivity
        _binding = FragmentScreen3Binding.inflate(inflater, container, false)
        loadingState(State.IS_LOADING)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        countriesAdapter = CountriesAdapter()
        if (::activity.isInitialized && countriesAdapter != null) {
            with(binding) {
                recyclerCountries.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = countriesAdapter
                }
            }
        }

        countriesAdapter?.let {
            it.updateList(listOfCountries)
            loadingState(State.IS_LOADED)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val listOfCountries = listOf(
            CountriesEntity("Colombia", "Bogotá", "YBR"),
            CountriesEntity("Spain", "Madrid", "RYR"),
            CountriesEntity("Germany", "Berlín", "BRY"),
            CountriesEntity("United States", "Washington", "BRW")
        )
    }

    private val listOfActions = mapOf(
        State.IS_LOADING to { isDataLoading() },
        State.IS_LOADED to { isDataLoaded() },
    )

    private fun loadingState(state: State) {
        val action = listOfActions[state]
        action?.invoke()
    }

    private fun isDataLoading() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            errorCanvas.visibility = View.GONE
        }
    }

    private fun isDataLoaded() {
        with(binding) {
            progressBar.visibility = View.GONE
            errorCanvas.visibility = View.VISIBLE
        }
    }

    enum class State {
        IS_LOADING,
        IS_LOADED
    }
}

interface LoadingHandler {
    fun handle()
}