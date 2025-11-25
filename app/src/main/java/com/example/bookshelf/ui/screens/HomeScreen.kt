package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.bookshelf.R
import com.example.bookshelf.model.Book
import com.example.bookshelf.ui.BooksMainViewModel
import com.example.bookshelf.ui.BooksUiState
import com.example.compose.BookShelfTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookHomeTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.bookshelf),
                style = MaterialTheme.typography.displaySmall
            )
        }
    )
}

@Composable
fun BookHomeScreen(
    uiState: BooksUiState,
    onBookClick: (String) -> Unit,
    onQueryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = { BookHomeTopAppBar() }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = padding,
            modifier = modifier
        ) {
            items(items = uiState.booksList, key = {book -> book.id }) { book ->
                BookImage(book = book)
            }
        }
    }
}

@Composable
fun BookImage(book: Book, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
    }
}


@Composable
@Preview
fun HomePreview() {
    BookShelfTheme {
        val viewModel: BooksMainViewModel = viewModel(factory = BooksMainViewModel.Factory)
        val uiState: BooksUiState by viewModel.book.collectAsState()
        BookHomeScreen(
            uiState = uiState,
            {},
            {}
        )
    }
}