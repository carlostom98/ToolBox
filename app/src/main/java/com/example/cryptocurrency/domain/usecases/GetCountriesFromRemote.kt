package com.example.cryptocurrency.domain.usecases

import com.example.cryptocurrency.data.retrofit.CountriesAPI
import com.example.cryptocurrency.domain.entities.CountriesEntity
import com.example.cryptocurrency.utils.BaseUseCaseNoParams
import org.json.JSONObject
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

