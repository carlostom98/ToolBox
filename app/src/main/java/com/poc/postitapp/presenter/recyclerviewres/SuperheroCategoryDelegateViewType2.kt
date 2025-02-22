package com.poc.postitapp.presenter.recyclerviewres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poc.postitapp.R
import com.poc.postitapp.databinding.SuperheroHideoutsBinding
import com.poc.postitapp.domain.entities.SuperheroHideouts
import com.poc.postitapp.domain.interfaces.AdapterItems
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