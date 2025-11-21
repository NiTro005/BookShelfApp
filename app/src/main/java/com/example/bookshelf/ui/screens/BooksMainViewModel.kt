package com.example.bookshelf.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.repository.BooksRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class BooksMainViewModel(private val repository: BooksRepository): ViewModel() {
    private val _books = MutableStateFlow(BooksUiState())
    val book: StateFlow<BooksUiState> = _books.asStateFlow()
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
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
                        currentBook = repository.getBookById(id)
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
                _books.update { currentState ->
                    val booksList = repository.getAllBooks(
                        query = query
                    )
                    currentState.copy(
                        booksList = booksList,
                        currentBook = repository.getBookById(booksList.first().id)
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
