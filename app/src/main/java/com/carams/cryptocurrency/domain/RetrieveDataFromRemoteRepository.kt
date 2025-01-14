package com.carams.cryptocurrency.domain

import com.carams.cryptocurrency.data.DataFromRemote
import com.carams.cryptocurrency.domain.entities.SuperHeroData
import com.carams.cryptocurrency.domain.interfaces.DataRepository
import io.reactivex.rxjava3.core.Single

class RetrieveDataFromRemoteRepository (private val remoteData: DataFromRemote) : DataRepository {
    override fun getFromRemote(): Single<List<SuperHeroData>> {
        return Single.fromCallable {
            remoteData.create()
        }
    }
}