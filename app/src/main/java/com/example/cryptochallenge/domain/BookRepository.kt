package com.example.cryptochallenge.domain

import com.example.cryptochallenge.data.model.*
import io.reactivex.Observable

interface BookRepository {
    fun getBooksWithRx(): Observable<GetBooks>
    suspend fun getBooks(): List<Book>
    suspend fun getBookDetails(book: String): Book
    suspend fun getBookOrders(book: String): BookOrders
}