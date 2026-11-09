package com.example.bookshelf.model

import com.google.gson.annotations.SerializedName

/**
 * Domain model representing a book in the UI.
 */
data class Book(
    val id: String,
    val title: String,
    val imgSrc: String?
)

/**
 * Response returned by Google Books search endpoint:
 * https://www.googleapis.com/books/v1/volumes?q={search_term}
 */
data class BooksSearchResponse(
    @SerializedName("kind") val kind: String? = null,
    @SerializedName("totalItems") val totalItems: Int? = null,
    @SerializedName("items") val items: List<BookItemResponse>? = null
)

/**
 * Represents a volume item in search results or volume endpoint.
 */
data class BookItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo? = null
)

/**
 * Details of the volume.
 */
data class VolumeInfo(
    @SerializedName("title") val title: String? = null,
    @SerializedName("authors") val authors: List<String>? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("imageLinks") val imageLinks: ImageLinks? = null
)

/**
 * Image links for a book volume.
 */
data class ImageLinks(
    @SerializedName("smallThumbnail") val smallThumbnail: String? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null
) {
    /**
     * Converts http URLs to https to ensure Coil can load them over a secure connection.
     */
    val httpsThumbnail: String?
        get() = (thumbnail ?: smallThumbnail)?.replace("http://", "https://")
}
