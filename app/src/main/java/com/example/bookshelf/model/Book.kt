package com.example.bookshelf.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val title: String,
    val description: String,
    val imageLink: String?
)
