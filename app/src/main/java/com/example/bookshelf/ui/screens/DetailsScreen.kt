package com.example.bookshelf.ui.screens

import android.icu.text.CaseMap
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookshelf.ui.BooksUiState
import com.example.bookshelf.ui.NavType

@Composable
fun BookDetailScreen(
    omBackClick: () -> Unit,
    uiState: BooksUiState,
    modifier: Modifier = Modifier,
    navType: NavType = NavType.LIST
) {

}

@Composable
fun DetailsTopBar(
    navType: Boolean,
    title: String,
    modifier: Modifier = Modifier) {
    TODO()
}
