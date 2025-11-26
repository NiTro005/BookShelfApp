package com.example.bookshelf.ui.screens

import android.icu.text.CaseMap
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bookshelf.R
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

        }
    }
}
