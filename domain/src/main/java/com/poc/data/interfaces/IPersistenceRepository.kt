package com.poc.data.interfaces

import com.poc.domain.entities.Response
import kotlinx.coroutines.flow.Flow

interface  IPersistenceRepository <in T, out R> {
    fun getAll(): Flow<Response<List<R>>>

}