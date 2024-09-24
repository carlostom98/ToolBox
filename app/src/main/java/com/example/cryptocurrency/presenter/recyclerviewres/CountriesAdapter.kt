package com.example.cryptocurrency.presenter.recyclerviewres

import com.example.cryptocurrency.domain.interfaces.AdapterItems
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class CountriesAdapter : SuperListDelegationAdapter() {
    private val countriesDelegate = CountriesCategoryDelegate()

    init {
        delegatesManager.addDelegate(countriesDelegate)
    }
}