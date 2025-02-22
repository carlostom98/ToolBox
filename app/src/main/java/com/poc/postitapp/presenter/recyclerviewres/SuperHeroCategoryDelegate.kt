package com.poc.postitapp.presenter.recyclerviewres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poc.postitapp.R
import com.poc.postitapp.databinding.SuperheroRowSketchBinding
import com.poc.postitapp.domain.interfaces.AdapterItems
import com.poc.postitapp.domain.entities.SuperHeroData
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class SuperHeroCategoryDelegate(
) : AdapterDelegate<List<AdapterItems>>() {

    override fun isForViewType(items: List<AdapterItems>, position: Int): Boolean =
        items[position] is SuperHeroData

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        SuperHeroViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.superhero_row_sketch, parent, false)
        )


    override fun onBindViewHolder(
        items: List<AdapterItems>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as SuperHeroData
        (holder as SuperHeroViewHolder).set(item)
    }

    inner class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SuperheroRowSketchBinding.bind(view)

        fun set(item: SuperHeroData) {
            with(binding) {
                superheroName.text = item.name
                superheroName.id = item.id.toInt()
            }
        }
    }

}