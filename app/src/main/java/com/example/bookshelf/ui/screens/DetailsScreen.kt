package com.example.bookshelf.ui.screens

import android.content.Intent
import android.icu.text.CaseMap
import android.telecom.Call
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil3.ImageLoader
import coil3.Uri
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.bookshelf.R
import com.example.bookshelf.model.BookUI
import com.example.bookshelf.ui.BooksUiState
import com.example.bookshelf.ui.NavType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = modifier
    )
}

@Composable
fun BookDetailScreen(
    omBackClick: () -> Unit,
    uiState: BooksUiState,
    modifier: Modifier = Modifier
) {
    val book = uiState.currentBook
    if(book != null) {
        Scaffold(
            topBar = {
                DetailsTopBar(
                    title = book.volumeInfo.title,
                    onBackClick = omBackClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ) { paddingValues ->
            BookDetailsContent(
                book = book,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
fun BookDetailsContent(
    book: BookUI,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = context)
                .data(book.volumeInfo.imageLinks?.small)
                .crossfade(true)
                .build(),
            contentDescription = book.volumeInfo.title,
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(R.drawable.loading_img),
            error = painterResource(R.drawable.ic_broken_image)
        )
        Spacer(Modifier.height(24.dp))
        Column(horizontalAlignment = Alignment.Start) {
            TextRow(stringResource(R.string.autors), book.volumeInfo.authors.toString())
            book.volumeInfo.pageCount?.let {TextRow(stringResource(R.string.page_count), it) }
            book.volumeInfo.description?.let {TextRow(stringResource(R.string.description), it)}
            book.webReaderLink?.let {
                Row {
                    Text(
                        text = stringResource(R.string.book_url),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = it,
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(it))
                                context.startActivity(intent)
                            }
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TextRow(title: String, text: String, modifier: Modifier = Modifier) {
    Row {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
