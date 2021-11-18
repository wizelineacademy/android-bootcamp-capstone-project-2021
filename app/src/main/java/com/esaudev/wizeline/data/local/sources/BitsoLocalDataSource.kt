package com.esaudev.wizeline.data.local.sources

import com.esaudev.wizeline.data.local.entities.AvailableBookEntity
import com.esaudev.wizeline.data.local.entities.OrderBookEntity
import com.esaudev.wizeline.data.local.entities.TickerEntity

interface BitsoLocalDataSource {

    suspend fun insertBook(book: AvailableBookEntity)

    suspend fun insertAllBooks(books: List<AvailableBookEntity>)

    suspend fun deleteAllBooks()

    suspend fun getAllBooks(): List<AvailableBookEntity>

    suspend fun insertOrderBook(orderBook: OrderBookEntity)

    suspend fun getOrderBookByBook(book: String): OrderBookEntity

    suspend fun insertBookTicker(ticker: TickerEntity)

    suspend fun getTickerByBook(book: String): TickerEntity
}