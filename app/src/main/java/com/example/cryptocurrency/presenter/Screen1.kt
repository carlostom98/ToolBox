package com.example.cryptocurrency.presenter

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency.MainActivity
import com.example.cryptocurrency.databinding.FragmentScreen1Binding
import com.example.cryptocurrency.domain.entities.SuperHeroData
import com.example.cryptocurrency.presenter.recyclerviewres.SuperHeroAdapter
import kotlin.random.Random


class Screen1 : Fragment() {

    private var _binding: FragmentScreen1Binding? = null
    private val binding get() = _binding!!
    private lateinit var superHeroAdapter: SuperHeroAdapter

    private lateinit var activity: Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity = getActivity() as MainActivity
        _binding = FragmentScreen1Binding.inflate(inflater, container, false)

        superHeroAdapter = SuperHeroAdapter(
            superheroes
        ) {
            superheroes = superheroes.minus(it)
            superHeroAdapter.updateList(superheroes)
        }
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
                    val random = Random.nextInt(1000).toString()
                    val superHero = SuperHeroData(random, random + "1")
                    superheroes = superheroes.plus(superHero)
                    superHeroAdapter.updateList(superheroes)
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
            SuperHeroData("androidman", "4")
        )
    }
}