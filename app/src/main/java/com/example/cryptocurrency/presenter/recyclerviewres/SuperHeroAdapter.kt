package com.example.cryptocurrency.presenter.recyclerviewres

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocurrency.domain.AdapterItems
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class SuperHeroAdapter : ListDelegationAdapter<List<AdapterItems>>() {

    fun updateList(newList: List<AdapterItems>) {
        val diffResult = DiffUtil.calculateDiff(ParentDiffUtil(this.items ?: emptyList(), newList))
        super.setItems(items)
        diffResult.dispatchUpdatesTo(this)
    }

    private val superHeroCategoryDelegate = SuperHeroCategoryDelegate()

    init {
        delegatesManager.addDelegate(superHeroCategoryDelegate)
    }

}


