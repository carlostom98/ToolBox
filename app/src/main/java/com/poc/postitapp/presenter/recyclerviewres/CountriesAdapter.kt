package com.poc.postitapp.presenter.recyclerviewres

class CountriesAdapter : SuperListDelegationAdapter() {
    private val countriesDelegate = CountriesCategoryDelegate()

    init {
        delegatesManager.addDelegate(countriesDelegate)
    }
}