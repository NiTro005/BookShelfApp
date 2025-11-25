package com.example.bookshelf.ui

sealed interface LoadingStatus {
    object Loading: LoadingStatus
    class Success(val message: String? = null): LoadingStatus
    class Error(val message: String? = null): LoadingStatus
}