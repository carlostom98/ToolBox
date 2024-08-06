package com.example.cryptocurrency.presenter.recyclerviewres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.R
import com.example.cryptocurrency.domain.entities.SuperHeroData

class SuperHeroAdapter(
    private var list: List<SuperHeroData>,
    private val onItemRemove: (SuperHeroData) -> Unit
) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(newList: List<SuperHeroData>) {
        val superHeroDiff = SuperHeroDiffUtil(list, newList)
        val result = DiffUtil.calculateDiff(superHeroDiff)
        list = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder =
        SuperHeroViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.superhero_row_sketch, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.render(list[position], onItemRemove)
    }

}