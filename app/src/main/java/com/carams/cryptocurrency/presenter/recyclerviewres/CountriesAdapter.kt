package com.carams.cryptocurrency.presenter.recyclerviewres

class CountriesAdapter : SuperListDelegationAdapter() {
    private val countriesDelegate = CountriesCategoryDelegate()

    init {
        delegatesManager.addDelegate(countriesDelegate)
    }
}