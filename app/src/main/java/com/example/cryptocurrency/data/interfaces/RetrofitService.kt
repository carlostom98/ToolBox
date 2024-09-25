package com.example.cryptocurrency.data.interfaces

import com.example.cryptocurrency.data.retrofit.API

interface RetrofitService {
    suspend fun getService(): Result<API>
}