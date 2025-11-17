package com.poc.data.interfaces

import com.poc.domain.entities.Response
import kotlinx.coroutines.flow.Flow

interface  IPersistenceRepository <out T> {
    fun getAll(): Flow<Result<List<T>>>

}