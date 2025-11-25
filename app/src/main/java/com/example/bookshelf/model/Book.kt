package com.example.bookshelf.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
data class BookUI(
    val id: String,
    val volumeInfo: VolumeInfo,
    val webReaderLink: String? = null
)

@Serializable
data class VolumeInfo(
    val title: String,
    val description: String? = null,
    val imageLink: ImageLink? = null,
    val authors: List<String> = emptyList(),
    val pageCount: String
)

@Serializable
data class ImageLink(
    val thumbnail: String? = null,
    val small: String? = null
)
