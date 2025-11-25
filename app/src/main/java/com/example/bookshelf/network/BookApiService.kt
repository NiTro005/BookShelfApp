package com.example.bookshelf.network

import com.example.bookshelf.model.BookUI
import com.example.bookshelf.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {
    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResult: Int = 20
    ) : List<Item>
    @GET("books/v1/volumes/{bookId}")
    suspend fun getBookById(
        @Path("bookId") bookId: String,
        @Query("key") apiKey: String
    ): BookUI
}