package com.example.bookshelf.ui.screens

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookshelf.ui.BooksUiState

@Composable
fun LoadingScreen(
    loadComplete: (Unit) -> Unit,
    uiState: BooksUiState,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {

    }
}