package com.poc.postitapp.presenter.viewintents.getcountriesintent

sealed class CountriesIntents {
    data object GetData: CountriesIntents()
}