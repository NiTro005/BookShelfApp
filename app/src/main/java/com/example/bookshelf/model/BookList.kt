package com.example.bookshelf.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@JsonIgnoreUnknownKeys
@Serializable
data class BookList(
    val items: List<Item> = emptyList()
)

@JsonIgnoreUnknownKeys
@Serializable
data class Item(
    val id: String,
    val volumeInfo: VolumeInfoLite
)

@JsonIgnoreUnknownKeys
@Serializable
data class VolumeInfoLite(
    val title: String,
    val imageLinks: ImageLinks? = null
)