package com.example.cryptocurrency.domain.entities

import com.example.cryptocurrency.domain.interfaces.AdapterItems

data class CountriesEntity(
    val countryName: String?,
    val capital: String?,
    val flag: String?,
) : AdapterItems {
    override fun isItemTheSame(other: AdapterItems): Boolean {
        return (other as? CountriesEntity)?.countryName == this.countryName
    }

    override fun isContentTheSame(other: AdapterItems): Boolean {
        return (other as? CountriesEntity) == this
    }

}