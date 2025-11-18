package com.poc.domain.usecases

import com.poc.data.interfaces.IPersistenceRepository
import com.poc.domain.entities.AlbumDetailedEntity
import com.poc.domain.entities.AlbumEntity
import com.poc.domain.entities.PhotosEntity
import com.poc.domain.entities.Response
import com.poc.domain.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GetAlbumsDetailedUseCaseTest {
    private val repositoryPhotos: IPersistenceRepository<PhotosEntity> = mockk()
    private val repositoryAlbums: IPersistenceRepository<AlbumEntity> = mockk()

    private lateinit var getAlbumsDetailedUseCaseTest: GetAlbumsDetailedUseCase

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        coEvery { repositoryPhotos.getAll() } returns flowOf(Result.success(listOf(PhotosEntity(1, title = TITLE_PHOTOS, url = URL))))
        coEvery { repositoryAlbums.getAll() } returns flowOf(Result.success(listOf(AlbumEntity(userId = 1, id = 1, title = TITLE_ALBUMS))))
        getAlbumsDetailedUseCaseTest = GetAlbumsDetailedUseCase(repositoryPhotos, repositoryAlbums)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun checkAlbumDetailedEntityAfterUseCase() = runTest {
        // Given
        val deferredResult = CompletableDeferred<Response<List<AlbumDetailedEntity>>>()
        //When
        getAlbumsDetailedUseCaseTest(this) { result ->
            deferredResult.complete(result)
        }
        val result = deferredResult.await()
        // Then
        assert(result is Response.Success)
        result as Response.Success<List<AlbumDetailedEntity>>
        assert(result.response?.firstOrNull()?.title == TITLE_ALBUMS)
        assert(result.response?.firstOrNull()?.photos?.firstOrNull()?.title == TITLE_PHOTOS)
    }

    companion object {
        const val TITLE_PHOTOS = "TITLE_PHOTOS"
        const val TITLE_ALBUMS = "TITLE_PHOTOS"
        const val URL = "http://mockk.com"
    }
}