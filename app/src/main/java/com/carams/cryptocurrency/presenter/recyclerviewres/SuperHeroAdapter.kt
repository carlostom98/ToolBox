package com.carams.cryptocurrency.presenter.recyclerviewres

class SuperHeroAdapter : SuperListDelegationAdapter() {

    private val superHeroCategoryDelegate = SuperHeroCategoryDelegate()
    private val superHeroCategoryDelegateView2 = SuperheroCategoryDelegateViewType2()

    init {
        delegatesManager.addDelegate(superHeroCategoryDelegate)
        delegatesManager.addDelegate(superHeroCategoryDelegateView2)
    }
}


