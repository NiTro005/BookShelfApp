package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class BookList(
    val items: List<ItemId> = emptyList()
)

@Serializable
data class ItemId(
    val id: String
)
