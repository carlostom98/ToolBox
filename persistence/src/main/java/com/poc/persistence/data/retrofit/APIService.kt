package com.poc.persistence.data.retrofit

import com.poc.persistence.data.entities.AlbumVO
import com.poc.persistence.data.entities.PhotosVO
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("albums")
    suspend fun getAlbumsData(): Response<List<AlbumVO>>

    @GET("photos")
    suspend fun getPhotosData(): Response<List<PhotosVO>>
}