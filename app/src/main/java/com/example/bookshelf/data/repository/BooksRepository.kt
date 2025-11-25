package com.example.bookshelf.data.repository

import com.example.bookshelf.model.BookList
import com.example.bookshelf.model.BookUI
import com.example.bookshelf.model.Item
import com.example.bookshelf.network.BookApiService

interface BooksRepository {
    suspend fun getAllBooks(query: String): BookList
    suspend fun getBookById(id: String): BookUI
}

class BooksNetworkRepository(
    private val service: BookApiService,
    private val apiKey: String
): BooksRepository {
    override suspend fun getAllBooks(query: String): BookList {
        val formatQuery = query.replace(" ", "+")
        return service.getBooks(
            query = formatQuery,
            apiKey = apiKey,
            maxResult = 40
            )
    }

    override suspend fun getBookById(id: String): BookUI {
        return service.getBookById(
            bookId = id,
            apiKey = apiKey
        )
    }
}