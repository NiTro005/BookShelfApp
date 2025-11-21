package com.example.bookshelf.data.repository

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.ItemId
import com.example.bookshelf.network.BookApiService

interface BooksRepository {
    suspend fun getAllBooks(query: String): List<ItemId>
    suspend fun getBookById(id: String): Book
}

class BooksNetworkRepository(
    private val service: BookApiService,
    private val apiKey: String
): BooksRepository {
    override suspend fun getAllBooks(query: String): List<ItemId> {
        val formatQuery = query.replace(" ", "+")
        return service.getBooks(
            query = formatQuery,
            apiKey = apiKey,
            maxResult = 40
            )
    }

    override suspend fun getBookById(id: String): Book {
        return service.getBookById(
            bookId = id,
            apiKey = apiKey
        )
    }
}