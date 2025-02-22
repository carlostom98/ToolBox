package com.poc.postitapp.domain

import com.poc.postitapp.data.DataFromRemote
import com.poc.postitapp.domain.entities.SuperHeroData
import com.poc.postitapp.domain.interfaces.DataRepository
import io.reactivex.rxjava3.core.Single

class RetrieveDataFromRemoteRepository (private val remoteData: DataFromRemote) : DataRepository {
    override fun getFromRemote(): Single<List<SuperHeroData>> {
        return Single.fromCallable {
            remoteData.create()
        }
    }
}