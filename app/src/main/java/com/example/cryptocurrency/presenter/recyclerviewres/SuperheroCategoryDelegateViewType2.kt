package com.example.cryptocurrency.presenter.recyclerviewres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cryptocurrency.R
import com.example.cryptocurrency.databinding.SuperheroHideoutsBinding
import com.example.cryptocurrency.databinding.SuperheroRowSketchBinding
import com.example.cryptocurrency.domain.entities.SuperheroHideouts
import com.example.cryptocurrency.domain.interfaces.AdapterItems
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class SuperheroCategoryDelegateViewType2 : AdapterDelegate<List<AdapterItems>>() {
    override fun isForViewType(items: List<AdapterItems>, position: Int): Boolean =
        items[position] is SuperheroHideouts


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return HideoutsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.superhero_hideouts, parent, false)
        )
    }

    override fun onBindViewHolder(
        items: List<AdapterItems>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as SuperheroHideouts
        (holder as HideoutsViewHolder).set(item)
    }

    inner class HideoutsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SuperheroHideoutsBinding.bind(view)
        fun set(item: SuperheroHideouts) {
            with(binding) {
                hideoutName.text = item.name
            }
        }
    }
}