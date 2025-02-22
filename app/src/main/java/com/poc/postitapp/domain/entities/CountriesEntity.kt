package com.poc.postitapp.domain.entities

import com.poc.postitapp.domain.interfaces.AdapterItems
import com.google.gson.annotations.SerializedName

data class CountriesEntity(
    @SerializedName("name")
    val countryName: String?,
    @SerializedName("capital")
    val capital: String?,
    @SerializedName("flagPNG")
    val flag: String?,
) : AdapterItems {
    override fun isItemTheSame(other: AdapterItems): Boolean {
        return (other as? CountriesEntity)?.countryName == this.countryName
    }

    override fun isContentTheSame(other: AdapterItems): Boolean {
        return (other as? CountriesEntity) == this
    }

}