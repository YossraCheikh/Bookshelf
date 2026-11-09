package com.example.bookshelf

import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.fake.FakeBooksRepository
import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.model.Book
import com.example.bookshelf.rules.TestDispatcherRule
import com.example.bookshelf.ui.BooksUiState
import com.example.bookshelf.ui.BooksViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class BooksViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun booksViewModel_getBooks_verifyBooksUiStateSuccess() = runTest {
        val booksViewModel = BooksViewModel(
            booksRepository = FakeBooksRepository()
        )
        assertEquals(
            BooksUiState.Success(FakeDataSource.booksList),
            booksViewModel.booksUiState
        )
    }

    @Test
    fun booksViewModel_getBooks_verifyBooksUiStateError() = runTest {
        val failingRepository = object : BooksRepository {
            override suspend fun getBooks(query: String): List<Book> {
                throw IOException("Network error")
            }

            override suspend fun getBook(id: String): Book? {
                throw IOException("Network error")
            }
        }

        val booksViewModel = BooksViewModel(
            booksRepository = failingRepository
        )
        assertTrue(booksViewModel.booksUiState is BooksUiState.Error)
    }
}
