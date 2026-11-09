package com.example.bookshelf.fake

import com.example.bookshelf.model.BookItemResponse
import com.example.bookshelf.model.BooksSearchResponse
import com.example.bookshelf.network.BooksApiService

class FakeBooksApiService : BooksApiService {
    override suspend fun searchBooks(query: String, maxResults: Int): BooksSearchResponse {
        return FakeDataSource.searchResponse
    }

    override suspend fun getBook(id: String): BookItemResponse {
        return when (id) {
            FakeDataSource.idOne -> FakeDataSource.bookDetailOne
            FakeDataSource.idTwo -> FakeDataSource.bookDetailTwo
            else -> throw IllegalArgumentException("Unknown book id: $id")
        }
    }
}
