package com.example.bookshelf.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookshelf.R
import com.example.bookshelf.ui.BooksUiState
import com.example.bookshelf.ui.LoadingStatus

@Composable
fun LoadingScreen(
    loadComplete: () -> Unit,
    retryAction: () -> Unit,
    uiState: BooksUiState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when (uiState.status) {
            is LoadingStatus.Error -> {
                Column(modifier = Modifier.padding(vertical = 16.dp)) {
                    Text(
                        text = uiState.status.message ?: "Unknown error"
                    )
                    Button(onClick = retryAction) {
                        Text("Retry")
                    }
                }
            }
            LoadingStatus.Loading -> {
                Column {
                    Text(
                        text = stringResource(R.string.loading),
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            }
            LoadingStatus.Success -> {
                loadComplete()
            }
        }
    }
}
