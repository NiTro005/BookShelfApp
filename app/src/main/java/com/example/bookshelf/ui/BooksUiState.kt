package com.example.bookshelf.ui

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.ItemId

data class BooksUiState(
    val booksList: List<Book> = emptyList(),
    val currentBook: Book? = null,
    val status: LoadingStatus = LoadingStatus.Loading
)