package com.example.bookshelf.ui

import com.example.bookshelf.model.BookUI
import com.example.bookshelf.model.Item

data class BooksUiState(
    val booksList: List<Item> = emptyList(),
    val currentBook: BookUI? = null,
    val status: LoadingStatus = LoadingStatus.Loading
)
