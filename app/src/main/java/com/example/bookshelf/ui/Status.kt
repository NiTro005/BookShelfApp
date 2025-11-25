package com.example.bookshelf.ui

sealed interface LoadingStatus {
    object Loading: LoadingStatus
    object Success: LoadingStatus
    class Error(val message: String? = null): LoadingStatus
}