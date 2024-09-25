package com.example.cryptocurrency.presenter.viewintents.getcountriesintent

sealed class CountriesIntents {
    data object GetData: CountriesIntents()
}