package com.example.bookshelf.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay

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
        BookHomeContent(
            uiState = uiState,
            onBookClick = onBookClick,
            onQueryClick = onQueryClick,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun BookHomeContent(
    uiState: BooksUiState,
    onQueryClick: (String) -> Unit,
    onBookClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var rememberTextField by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(rememberTextField) {
        delay(500)
        if (!rememberTextField.isEmpty()) onQueryClick(rememberTextField)
    }
    Box(modifier = modifier) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = uiState.booksList, key = { book -> book.id }) { book ->
                BookImage(
                    book = book, modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth().clickable(
                            onClick = { onBookClick(book.id) }
                        ))
            }
        }
        BookQueryField(
            value = rememberTextField,
            onValueChange = { rememberTextField = it},
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp)
        )
    }
}

@Composable
fun BookQueryField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label =  {Text("Поиск")},
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                focusedSupportingTextColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = RoundedCornerShape(10.dp)
        )
    }
}

@Composable
fun BookImage(book: Item, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(book.volumeInfo.imageLinks?.thumbnail)
                .crossfade(true)
                .build(),
            contentDescription = book.volumeInfo.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.loading_img),
            error = painterResource(R.drawable.ic_broken_image),
            modifier = Modifier.align(Alignment.Center).fillMaxSize()
        )
        if(book.volumeInfo.imageLinks?.thumbnail == null) {
            Text(
                text = book.volumeInfo.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.BottomCenter),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
