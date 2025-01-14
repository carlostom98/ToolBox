package com.carams.cryptocurrency.domain.interfaces

import com.carams.cryptocurrency.domain.entities.SuperHeroData
import io.reactivex.rxjava3.core.Single

interface DataRepository {
    fun getFromRemote(): Single<List<SuperHeroData>>
}