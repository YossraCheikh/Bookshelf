package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BooksApiService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

/**
 * Repository interface providing methods to fetch books.
 */
interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>
    suspend fun getBook(id: String): Book?
}

/**
 * Network implementation of [BooksRepository] that uses [BooksApiService].
 */
class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {

    override suspend fun getBooks(query: String): List<Book> = coroutineScope {
        val searchResponse = booksApiService.searchBooks(query)
        val items = searchResponse.items ?: return@coroutineScope emptyList()

        items.map { item ->
            async {
                try {
                    val detail = booksApiService.getBook(item.id)
                    val thumbnail = detail.volumeInfo?.imageLinks?.httpsThumbnail
                        ?: item.volumeInfo?.imageLinks?.httpsThumbnail
                    val title = detail.volumeInfo?.title
                        ?: item.volumeInfo?.title
                        ?: "No Title"

                    Book(
                        id = item.id,
                        title = title,
                        imgSrc = thumbnail
                    )
                } catch (e: Exception) {
                    Book(
                        id = item.id,
                        title = item.volumeInfo?.title ?: "No Title",
                        imgSrc = item.volumeInfo?.imageLinks?.httpsThumbnail
                    )
                }
            }
        }.awaitAll()
    }

    override suspend fun getBook(id: String): Book? {
        return try {
            val detail = booksApiService.getBook(id)
            Book(
                id = detail.id,
                title = detail.volumeInfo?.title ?: "No Title",
                imgSrc = detail.volumeInfo?.imageLinks?.httpsThumbnail
            )
        } catch (e: Exception) {
            null
        }
    }
}
