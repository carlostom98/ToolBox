package com.poc.postitapp.domain.interfaces

import com.poc.postitapp.domain.entities.SuperHeroData
import io.reactivex.rxjava3.core.Single

interface DataRepository {
    fun getFromRemote(): Single<List<SuperHeroData>>
}