package com.example.cryptocurrency.presenter.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency.databinding.FragmentScreen3Binding
import com.example.cryptocurrency.domain.entities.CountriesEntity
import com.example.cryptocurrency.presenter.MainActivity
import com.example.cryptocurrency.presenter.recyclerviewres.CountriesAdapter
import com.example.cryptocurrency.presenter.viewintents.ViewStates
import com.example.cryptocurrency.presenter.viewintents.getcountriesintent.CountriesIntents
import com.example.cryptocurrency.presenter.viewintents.getcountriesintent.GetCountriesFromRemoteViewModel
import kotlinx.coroutines.launch


class MealFragment : Fragment() {

    private var _binding: FragmentScreen3Binding? = null
    private val binding get() = _binding!!
    private var countriesAdapter: CountriesAdapter? = null

    private val getCountriesViewModel: GetCountriesFromRemoteViewModel by activityViewModels()

    private lateinit var activity: Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity = getActivity() as MainActivity
        _binding = FragmentScreen3Binding.inflate(inflater, container, false)
        loadingState(State.IS_LOADING)

        lifecycleScope.launch {
            getCountriesViewModel.mainState.collect { mainState ->
                when(mainState) {
                    is ViewStates.Error -> loadingState(State.IS_ERROR)
                    ViewStates.Idle -> Log.d("View", "idle")
                    is ViewStates.LoadData<*> -> {
                        val listOfCountries = mainState.data as List<CountriesEntity>
                        countriesAdapter?.updateList(listOfCountries)
                        loadingState(State.IS_LOADED)
                    }
                    ViewStates.Loading -> loadingState(State.IS_LOADING)
                }
            }
        }

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

                refreshButton.setOnClickListener {
                    lifecycleScope.launch {
                        getCountriesViewModel.userIntent.send(CountriesIntents.GetData)
                    }
                }
            }
        }

        countriesAdapter?.let {
            loadingState(State.IS_LOADED)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private val listOfActions = mapOf(
        State.IS_LOADING to { isDataLoading() },
        State.IS_LOADED to { isDataLoaded() },
        State.IS_ERROR to { onErrorLoading() },
    )

    private fun loadingState(state: State) {
        val action = listOfActions[state]
        action?.invoke()
    }

    private fun isDataLoading() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            errorCanvas.visibility = View.GONE
            canvasData.visibility = View.GONE
        }
    }

    private fun isDataLoaded() {
        with(binding) {
            progressBar.visibility = View.GONE
            errorCanvas.visibility = View.GONE
            canvasData.visibility = View.VISIBLE
        }
    }

    private fun onErrorLoading() {
        with(binding) {
            progressBar.visibility = View.GONE
            errorCanvas.visibility = View.VISIBLE
            "Something went wrong".also { textError.text = it }
            canvasData.visibility = View.GONE
        }
    }

    enum class State {
        IS_LOADING,
        IS_LOADED,
        IS_ERROR
    }
}

interface LoadingHandler {
    fun handle()
}