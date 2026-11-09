package com.example.bookshelf.network

import com.example.bookshelf.model.BookItemResponse
import com.example.bookshelf.model.BooksSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
    /**
     * Search books by query parameter `q`.
     */
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 20
    ): BooksSearchResponse

    /**
     * Get details for a specific book by its volume ID.
     */
    @GET("volumes/{id}")
    suspend fun getBook(
        @Path("id") id: String
    ): BookItemResponse
}
