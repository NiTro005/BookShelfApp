package com.example.bookshelf.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookshelf.ui.screens.BookDetailsContent
import com.example.bookshelf.ui.screens.BookHomeContent
import com.example.bookshelf.ui.screens.BookHomeTopAppBar

@Composable
fun ExtractSizeApp(
    uiState: BooksUiState,
    onQueryClick: (String) -> Unit,
    onBookClick: (String) -> Unit,
    onBackAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackAction()
    }
    val book = uiState.currentBook
    Scaffold(
        topBar = { BookHomeTopAppBar(modifier = Modifier.fillMaxSize()) },
        modifier = modifier
    ) { padding ->
        Row {
            BookHomeContent(
                uiState = uiState,
                onBookClick = onBookClick,
                onQueryClick = onQueryClick,
                modifier = Modifier.padding(padding).weight(2f)
            )
            if (book != null) {
                BookDetailsContent(
                    book = book,
                    modifier = Modifier.padding(padding).weight(1f)
                )
            }
        }
    }
}
