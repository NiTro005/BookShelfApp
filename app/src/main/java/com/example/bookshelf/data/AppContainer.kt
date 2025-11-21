package com.example.bookshelf.data

import com.example.bookshelf.data.repository.BooksNetworkRepository
import com.example.bookshelf.data.repository.BooksRepository
import com.example.bookshelf.network.BookApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

interface AppContainer {
    val repository: BooksRepository
}

class DefaultAppContainer: AppContainer {

    private val baseUrl = "https://www.googleapis.com"
    private val apiKey = "AIzaSyDRfMrJrN4AT4LcPmT-tUUWkfQjA2S4k3U"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val apiService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }

    override val repository: BooksRepository by lazy {
        BooksNetworkRepository(
            service = apiService,
            apiKey = apiKey
        )
    }
}