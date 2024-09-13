package com.example.cryptocurrency.domain.entities

import com.example.cryptocurrency.domain.interfaces.AdapterItems

data class SuperheroHideouts(val name: String, val type: String, val superHero: SuperHeroData? = null): AdapterItems {
    override fun isItemTheSame(other: AdapterItems): Boolean {
        return (other as? SuperheroHideouts)?.name == this.name
    }

    override fun isContentTheSame(other: AdapterItems): Boolean {
        return (other as? SuperheroHideouts) == this
    }

}
