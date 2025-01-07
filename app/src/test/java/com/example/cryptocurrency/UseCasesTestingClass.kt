package com.example.cryptocurrency

import com.example.cryptocurrency.data.retrofit.CountriesAPI
import com.example.cryptocurrency.domain.entities.CountriesEntity
import com.example.cryptocurrency.domain.usecases.GetCountriesFromRemote
import com.example.cryptocurrency.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response


class UseCasesTestingClass {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @MockK
    private lateinit var countriesApiMock: CountriesAPI
    private lateinit var getCountriesFromRemote: GetCountriesFromRemote

    private val listOfCountriesMocked = listOf(
        CountriesEntity("Colombia", "BTA", "mockFlagColombia"),
        CountriesEntity("Argentina", "BA", "mockFlagArgentina"),
        CountriesEntity("EEUU", "WSHT", "mockFlagEeuu"),
    )

    @Before
    fun setup() {
        getCountriesFromRemote = GetCountriesFromRemote(countriesApiMock)
    }

    @Test
    fun responseSuccessfullyRetrieved(): Unit = runBlocking {
        // Given
        coEvery { countriesApiMock.getCountries() } returns Response.success(listOfCountriesMocked)
        // When
        val partialResult = CompletableDeferred<Result<List<CountriesEntity>>>()
        getCountriesFromRemote(this) {
            partialResult.complete(it)
        }
        val result = partialResult.await()
        // Then
        coVerify (exactly = 1) { countriesApiMock.getCountries() }
        assert(result.isSuccess)
        result.onSuccess { countries ->
                assert(countries.size == listOfCountriesMocked.size)
                assert(countries == listOfCountriesMocked)
        }

    }

}