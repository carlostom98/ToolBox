package com.example.cryptocurrency.presenter.recyclerviewres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.R
import com.example.cryptocurrency.databinding.SuperheroRowSketchBinding
import com.example.cryptocurrency.domain.interfaces.AdapterItems
import com.example.cryptocurrency.domain.entities.SuperHeroData
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