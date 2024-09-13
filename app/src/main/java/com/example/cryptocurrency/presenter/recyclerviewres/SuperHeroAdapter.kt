package com.example.cryptocurrency.presenter.recyclerviewres

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocurrency.domain.interfaces.AdapterItems
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class SuperHeroAdapter : ListDelegationAdapter<List<AdapterItems>>() {

    fun updateList(newList: List<AdapterItems>) {
        val diffResult = DiffUtil.calculateDiff(ParentDiffUtil(this.items ?: emptyList(), newList))
        this.items = newList
        super.setItems(items)
        diffResult.dispatchUpdatesTo(this)
    }

    private val superHeroCategoryDelegate = SuperHeroCategoryDelegate()
    private val superHeroCategoryDelegateView2 = SuperheroCategoryDelegateViewType2()

    init {
        delegatesManager.addDelegate(superHeroCategoryDelegate)
        delegatesManager.addDelegate(superHeroCategoryDelegateView2)
    }

}


