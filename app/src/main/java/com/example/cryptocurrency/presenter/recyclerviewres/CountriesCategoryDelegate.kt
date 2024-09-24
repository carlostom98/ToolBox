package com.example.cryptocurrency.presenter.recyclerviewres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.R
import com.example.cryptocurrency.databinding.CountriesCategoryBinding
import com.example.cryptocurrency.domain.entities.CountriesEntity
import com.example.cryptocurrency.domain.interfaces.AdapterItems
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class CountriesCategoryDelegate : AdapterDelegate<List<AdapterItems>>() {
    override fun isForViewType(items: List<AdapterItems>, position: Int): Boolean =
        items[position] is CountriesEntity

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CountriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.countries_category, parent, false)
        )
    }

    override fun onBindViewHolder(
        items: List<AdapterItems>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as CountriesEntity
        (holder as CountriesViewHolder).set(item)
    }

    inner class CountriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CountriesCategoryBinding.bind(view)
        fun set(item: CountriesEntity) {
            with(binding) {
                countryName.text = item.countryName
            }
        }
    }

}