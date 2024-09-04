package com.example.cryptocurrency.presenter.recyclerviewres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.R
import com.example.cryptocurrency.domain.entities.SuperHeroData

class SuperHeroAdapter(
    private var list: List<SuperHeroData>
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
        val element = list[position]
        holder.superHeroName.text = element.name

        if (selectedIdsList.contains(element.id)) {
            holder.superHeroName.isSelected = true
        }else {
            holder.superHeroName.isSelected = false
        }

        holder.superHeroName.setOnClickListener {
            if(!selectedIdsList.contains(element.id)) {
                selectedIdsList.add(element.id)
                holder.superHeroName.isSelected = true
            }else {
                selectedIdsList.remove(element.id)
                selectedModelsList.remove(element)
                holder.superHeroName.isSelected = false
            }
        }
    }

    companion object {
        var selectedIdsList = mutableListOf<String>()
        var selectedModelsList = mutableListOf<SuperHeroData>()
    }

}