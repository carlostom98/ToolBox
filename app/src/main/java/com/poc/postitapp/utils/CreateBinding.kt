package com.poc.postitapp.utils

import androidx.viewbinding.ViewBinding


class CreateBinding <T: ViewBinding> {

    fun create(): T {
        val _binding: T ? = null
        return _binding ?: throw NullPointerException("Binding is null")
    }
}