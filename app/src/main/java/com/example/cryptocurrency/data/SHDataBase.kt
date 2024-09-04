package com.example.cryptocurrency.data

import com.example.cryptocurrency.domain.entities.SuperHeroData


interface DataFromRemote{
    fun create(): List<SuperHeroData>
}