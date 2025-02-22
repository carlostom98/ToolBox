package com.poc.postitapp.data

import com.poc.postitapp.domain.entities.SuperHeroData


interface DataFromRemote{
    fun create(): List<SuperHeroData>
}