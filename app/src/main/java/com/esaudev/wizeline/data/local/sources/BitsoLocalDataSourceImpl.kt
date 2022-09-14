package com.esaudev.wizeline.data.local.sources

import com.esaudev.wizeline.data.local.BitsoDao
import com.esaudev.wizeline.data.local.entities.AvailableBookEntity
import com.esaudev.wizeline.data.local.entities.OrderBookEntity
import com.esaudev.wizeline.data.local.entities.TickerEntity
import javax.inject.Inject

class BitsoLocalDataSourceImpl @Inject constructor(
    private val bitsoDao: BitsoDao
) : BitsoLocalDataSource {
    override suspend fun insertBook(book: AvailableBookEntity) {
        bitsoDao.insertBook(book)
    }

    override suspend fun insertAllBooks(books: List<AvailableBookEntity>) {
        bitsoDao.insertAllBooks(books)
    }

    override suspend fun deleteAllBooks() {
        bitsoDao.deleteAllBooks()
    }

    override suspend fun getAllBooks(): List<AvailableBookEntity> {
        return bitsoDao.getAllBooks()
    }

    override suspend fun insertOrderBook(orderBook: OrderBookEntity) {
        bitsoDao.insertOrderBook(orderBook)
    }

    override suspend fun getOrderBookByBook(book: String): OrderBookEntity {
        return bitsoDao.getOrderBookByBook(book)
    }

    override suspend fun insertBookTicker(ticker: TickerEntity) {
        bitsoDao.insertBookTicker(ticker)
    }

    override suspend fun getTickerByBook(book: String): TickerEntity {
        return bitsoDao.getTickerFromBook(book)
    }
}
