package com.poc.postitapp.domain.usecases

import com.poc.postitapp.data.retrofit.CountriesAPI
import com.poc.postitapp.domain.entities.CountriesEntity
import com.poc.postitapp.utils.BaseUseCaseNoParams
import javax.inject.Inject

class GetCountriesFromRemote @Inject constructor (private val countriesAPI: CountriesAPI): BaseUseCaseNoParams<Result<List<CountriesEntity>>>() {

    override suspend fun doWork(): Result<List<CountriesEntity>> {
        return try {
            val response = countriesAPI.getCountries()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Throwable("Response Error Code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

