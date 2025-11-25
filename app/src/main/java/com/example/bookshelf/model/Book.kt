package com.example.bookshelf.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@JsonIgnoreUnknownKeys
@Serializable
data class BookUI(
    val id: String,
    val volumeInfo: VolumeInfo,
    val webReaderLink: String? = null
)

@JsonIgnoreUnknownKeys
@Serializable
data class VolumeInfo(
    val title: String,
    val description: String? = null,
    val imageLinks: ImageLinks? = null,
    val authors: List<String> = emptyList(),
    val pageCount: String? = null
)

@JsonIgnoreUnknownKeys
@Serializable
data class ImageLinks(
    val thumbnail: String? = null,
    val small: String? = null
)
