package com.poc.postitapp.domain.entities

import com.poc.postitapp.domain.interfaces.AdapterItems

data class SuperHeroData(val name: String, val id: String, val superPower: String = "water") :
    AdapterItems {
    override fun isItemTheSame(other: AdapterItems): Boolean {
        return (other as? SuperHeroData)?.id == this.id
    }

    override fun isContentTheSame(other: AdapterItems): Boolean {
        return other as? SuperHeroData == this
    }
}

