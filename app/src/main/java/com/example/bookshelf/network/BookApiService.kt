package com.example.bookshelf.network

import com.example.bookshelf.model.Book
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {
    @GET("/books/v1/volumes")
    fun getBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResult: Int = 20
    ) : List<String>
    @GET("/books/v1/volumes/{bookId}")
    fun getBookById(
        @Path("bookId") bookId: String,
        @Query("key") apiKey: String
    ): Book
}