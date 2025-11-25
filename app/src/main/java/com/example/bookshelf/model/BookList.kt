package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class BookList(
    val items: List<Item> = emptyList()
)

@Serializable
data class Item(
    val id: String,
    val volumeInfo: VolumeInfoLite
)

@Serializable
data class VolumeInfoLite(
    val title: String,
    val imageLink: ImageLink? = null
)