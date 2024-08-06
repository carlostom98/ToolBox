package com.example.cryptocurrency.presenter.recyclerviewres

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.databinding.SuperheroRowSketchBinding
import com.example.cryptocurrency.domain.entities.SuperHeroData

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = SuperheroRowSketchBinding.bind(view)

    fun render(superHeroData: SuperHeroData, onItemRemove: (SuperHeroData) -> Unit) {
        with(binding) {
            superheroName.text = superHeroData.name
            superheroName.setOnClickListener {
                onItemRemove(superHeroData)
            }
            superheroId.text = superHeroData.id
        }
    }
}