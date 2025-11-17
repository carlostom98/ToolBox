package com.poc.viewmodel.viewintents.crudintent

sealed class UserIntent {
data object GetAllData : UserIntent()
}