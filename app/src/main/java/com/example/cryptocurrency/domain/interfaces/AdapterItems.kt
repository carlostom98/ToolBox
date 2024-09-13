package com.example.cryptocurrency.domain.interfaces

interface AdapterItems {
    fun isItemTheSame(other: AdapterItems): Boolean
    fun isContentTheSame(other: AdapterItems): Boolean
}