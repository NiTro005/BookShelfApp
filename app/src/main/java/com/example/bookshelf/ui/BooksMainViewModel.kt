package com.example.bookshelf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.repository.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class BooksMainViewModel(private val repository: BooksRepository): ViewModel() {
    private val _books = MutableStateFlow(BooksUiState())
    val book: StateFlow<BooksUiState> = _books.asStateFlow()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY] as BookShelfApplication)
                val bookRepository = application.container.repository
                BooksMainViewModel(bookRepository)
            }
        }
    }

    init {
        searchBooks("Star Wars")
    }

    fun bookDetails(id: String) {
        viewModelScope.launch {
            _books.update { it.copy(status = LoadingStatus.Loading)}
            try {
                _books.update {
                    it.copy(
                        currentBook = repository.getBookById(id),
                        status = LoadingStatus.Success
                    )
                }
            } catch (e: Exception) {
                _books.update { it.copy(
                    booksList = emptyList(),
                    currentBook = null,
                    status = LoadingStatus.Error(e.message)
                ) }
            }
        }
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            _books.update { it.copy(status = LoadingStatus.Loading)}
            try {
                val booksList = repository.getAllBooks(
                    query = query
                )
                val firstBook = booksList.firstOrNull()?.let { repository.getBookById(it.id) }
                _books.update { currentState ->
                    currentState.copy(
                        booksList = booksList,
                        currentBook = firstBook,
                        status = LoadingStatus.Success
                    )
                }
            } catch (e: Exception) {
                _books.update { it.copy(
                    booksList = emptyList(),
                    currentBook = null,
                    status = LoadingStatus.Error(e.message)
                ) }
            }
        }
    }
}