package com.example.cryptocurrency.domain.interfaces

import com.example.cryptocurrency.domain.entities.SuperHeroData
import io.reactivex.rxjava3.core.Single

interface DataRepository {
    fun getFromRemote(): Single<List<SuperHeroData>>
}