//package com.poc.postitapp
//
//import com.poc.postitapp.data.retrofit.CountriesAPI
//import com.poc.postitapp.domain.entities.CountriesEntity
//import com.poc.postitapp.domain.usecases.GetCountriesFromRemote
//import com.poc.postitapp.rules.MainDispatcherRule
//import io.mockk.coEvery
//import io.mockk.coVerify
//import io.mockk.impl.annotations.MockK
//import io.mockk.junit4.MockKRule
//import kotlinx.coroutines.CompletableDeferred
//import kotlinx.coroutines.test.runTest
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.ResponseBody.Companion.toResponseBody
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import retrofit2.Response
//
//
//class GetCountriesFromRemoteTest {
//
//    @get:Rule
//    val mockkRule = MockKRule(this)
//
//    @get:Rule
//    val mainDispatcherRule = MainDispatcherRule()
//
//
//    @MockK
//    private lateinit var countriesApiMock: CountriesAPI
//    private lateinit var getCountriesFromRemote: GetCountriesFromRemote
//
//    private val listOfCountriesMocked = listOf(
//        CountriesEntity("Colombia", "BTA", "mockFlagColombia"),
//        CountriesEntity("Argentina", "BA", "mockFlagArgentina"),
//        CountriesEntity("EEUU", "WSHT", "mockFlagEeuu"),
//    )
//
//    @Before
//    fun setup() {
//        getCountriesFromRemote = GetCountriesFromRemote(countriesApiMock)
//    }
//
//    @Test
//    fun responseSuccessfullyRetrieved(): Unit = runTest {
//        // Given
//        coEvery { countriesApiMock.getCountries() } returns Response.success(listOfCountriesMocked)
//        // When
//        val partialResult = CompletableDeferred<Result<List<CountriesEntity>>>()
//        getCountriesFromRemote(this) {
//            partialResult.complete(it)
//        }
//        val result = partialResult.await()
//        // Then
//        coVerify (exactly = 1) { countriesApiMock.getCountries() }
//        assert(result.isSuccess)
//        result.onSuccess { countries ->
//                assert(countries.size == listOfCountriesMocked.size)
//                assert(countries == listOfCountriesMocked)
//        }
//
//    }
//
//    @Test
//    fun responseExceptionRetrieved(): Unit = runTest {
//        // Given
//        val mockedErrorMessage = "mockkError"
//
//        coEvery { countriesApiMock.getCountries() } throws Exception(mockedErrorMessage)
//        // When
//        val partialResult = CompletableDeferred<Result<List<CountriesEntity>>>()
//        getCountriesFromRemote(this) {
//            partialResult.complete(it)
//        }
//        val result = partialResult.await()
//        // Then
//        coVerify (exactly = 1) { countriesApiMock.getCountries() }
//        assert(result.isFailure)
//        result.onFailure { errorMessage ->
//                assert(errorMessage.message == mockedErrorMessage)
//        }
//    }
//
//    @Test
//    fun responseSuccessfullyWithErrorCode(): Unit = runTest {
//        // Given
//        val errorCode = 400
//        val mockedErrorMessage = """
//            {
//                "message": "mockkError"
//            }
//        """.trimIndent()
//
//        coEvery { countriesApiMock.getCountries() } returns Response.error(errorCode, mockedErrorMessage.toResponseBody())
//        // When
//        val partialResult = CompletableDeferred<Result<List<CountriesEntity>>>()
//        getCountriesFromRemote(this) {
//            partialResult.complete(it)
//        }
//        val result = partialResult.await()
//        // Then
//        coVerify (exactly = 1) { countriesApiMock.getCountries() }
//        assert(result.isFailure)
//        result.onFailure { errorMessage ->
//            assert(errorMessage.message == "Response Error Code: $errorCode")
//        }
//    }
//
//}
//
//fun String.convertInResponseBody() = this.toResponseBody("text/plain".toMediaTypeOrNull())
