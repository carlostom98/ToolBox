package com.example.cryptocurrency.domain

import com.example.cryptocurrency.data.DataFromRemote
import com.example.cryptocurrency.domain.entities.SuperHeroData
import com.example.cryptocurrency.domain.interfaces.DataRepository
import io.reactivex.rxjava3.core.Single

class RetrieveDataFromRemoteRepository (private val remoteData: DataFromRemote) : DataRepository {
    override fun getFromRemote(): Single<List<SuperHeroData>> {
        return Single.fromCallable {
            remoteData.create()
        }
    }

}