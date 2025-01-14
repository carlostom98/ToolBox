package com.carams.cryptocurrency.data

import com.carams.cryptocurrency.domain.entities.SuperHeroData


interface DataFromRemote{
    fun create(): List<SuperHeroData>
}