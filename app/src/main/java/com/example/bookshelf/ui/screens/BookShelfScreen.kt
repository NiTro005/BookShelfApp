package com.example.bookshelf.ui.screens

import androidx.annotation.StringRes
import com.example.bookshelf.R

sealed class BookShelfScreen(@StringRes val title: Int? = null) {
    object Load: BookShelfScreen()
    object Home: BookShelfScreen(R.string.bookshelf)
    object HomeAndDetails: BookShelfScreen(R.string.bookshelf)
    data class Details(val bookId: String): BookShelfScreen(R.string.book_title)
}
