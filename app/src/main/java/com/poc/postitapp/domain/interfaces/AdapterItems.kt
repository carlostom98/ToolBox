package com.poc.postitapp.domain.interfaces

interface AdapterItems {
    fun isItemTheSame(other: AdapterItems): Boolean
    fun isContentTheSame(other: AdapterItems): Boolean
}