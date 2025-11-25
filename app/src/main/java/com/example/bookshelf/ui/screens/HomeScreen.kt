package com.example.bookshelf.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.packInts
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.bookshelf.R
import com.example.bookshelf.model.BookUI
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.Item
import com.example.bookshelf.model.VolumeInfoLite
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
            columns = GridCells.Adaptive(150.dp),
            contentPadding = padding,
            modifier = modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = uiState.booksList, key = {book -> book.id }) { book ->
                BookImage(book = book, modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth())
            }
        }
    }
}

@Composable
fun BookImage(book: Item, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Column() {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.volumeInfo.imageLinks?.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = book.volumeInfo.title,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.ic_broken_image),
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = book.volumeInfo.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Composable
@Preview
fun HomePreview() {
    BookShelfTheme {
        BookHomeScreen(
            uiState = BooksUiState(booksList = listOf(
                Item(id = "9390304", volumeInfo = VolumeInfoLite(title = "fjf",)),
                Item(id = "fefdf", volumeInfo = VolumeInfoLite(title = "StarWars"))
            )),
            {},
            {}
        )
    }
}