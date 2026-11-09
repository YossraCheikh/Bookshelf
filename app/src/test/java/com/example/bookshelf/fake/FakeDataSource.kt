package com.example.bookshelf.fake

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.BookItemResponse
import com.example.bookshelf.model.BooksSearchResponse
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo

object FakeDataSource {
    const val idOne = "book1"
    const val idTwo = "book2"
    const val titleOne = "The History of Jazz"
    const val titleTwo = "Jazz Styles"
    const val thumbOne = "http://books.google.com/books/content?id=1&img=1"
    const val thumbTwo = "http://books.google.com/books/content?id=2&img=1"

    const val secureThumbOne = "https://books.google.com/books/content?id=1&img=1"
    const val secureThumbTwo = "https://books.google.com/books/content?id=2&img=1"

    val searchResponse = BooksSearchResponse(
        totalItems = 2,
        items = listOf(
            BookItemResponse(
                id = idOne,
                volumeInfo = VolumeInfo(
                    title = titleOne,
                    imageLinks = ImageLinks(thumbnail = thumbOne)
                )
            ),
            BookItemResponse(
                id = idTwo,
                volumeInfo = VolumeInfo(
                    title = titleTwo,
                    imageLinks = ImageLinks(thumbnail = thumbTwo)
                )
            )
        )
    )

    val bookDetailOne = BookItemResponse(
        id = idOne,
        volumeInfo = VolumeInfo(
            title = titleOne,
            imageLinks = ImageLinks(thumbnail = thumbOne)
        )
    )

    val bookDetailTwo = BookItemResponse(
        id = idTwo,
        volumeInfo = VolumeInfo(
            title = titleTwo,
            imageLinks = ImageLinks(thumbnail = thumbTwo)
        )
    )

    val booksList = listOf(
        Book(
            id = idOne,
            title = titleOne,
            imgSrc = secureThumbOne
        ),
        Book(
            id = idTwo,
            title = titleTwo,
            imgSrc = secureThumbTwo
        )
    )
}
