package com.poc.postitapp.domain.usecases

import com.poc.postitapp.domain.usecases.persistancecases.AddPostItUseCase
import com.poc.postitapp.domain.usecases.persistancecases.DeletePostItUseCase
import com.poc.postitapp.domain.usecases.persistancecases.GetAllPostItsUseCase
import javax.inject.Inject

data class UseCases @Inject constructor(
    val deletePostItUseCase: DeletePostItUseCase,
    val getAllPostItsUseCase: GetAllPostItsUseCase,
    val addPostItUseCase: AddPostItUseCase
)
