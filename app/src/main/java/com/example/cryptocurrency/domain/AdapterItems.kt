package com.example.cryptocurrency.domain

interface AdapterItems {
    fun isItemTheSame(other: AdapterItems): Boolean
    fun isContentTheSame(other: AdapterItems): Boolean
}