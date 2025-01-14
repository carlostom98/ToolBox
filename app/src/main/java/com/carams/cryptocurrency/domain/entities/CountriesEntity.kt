package com.carams.cryptocurrency.domain.entities

import com.carams.cryptocurrency.domain.interfaces.AdapterItems
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