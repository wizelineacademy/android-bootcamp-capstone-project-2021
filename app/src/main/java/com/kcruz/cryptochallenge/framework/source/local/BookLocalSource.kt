package com.kcruz.cryptochallenge.framework.source.local

import com.kcruz.cryptochallenge.data.source.local.IBookLocalSource
import com.kcruz.cryptochallenge.domain.ExchangeOrderBook
import com.kcruz.cryptochallenge.framework.database.AppDatabase
import com.kcruz.cryptochallenge.framework.source.toEntity
import com.kcruz.cryptochallenge.framework.source.toModel

class BookLocalSource(database: AppDatabase): IBookLocalSource {
    private val exchangeOrderBookDao = database.exchangeOrderBookDao

    override fun getExchangeOrderBooks(): List<ExchangeOrderBook> {
        val books = mutableListOf<ExchangeOrderBook>()
        val entityBooks = exchangeOrderBookDao.getExchangeOrderBooks()

        for (book in entityBooks) {
            books.add(book.toModel())
        }

        return books
    }

    override fun saveExchangeOrderBooks(exchangeOrderBooks: List<ExchangeOrderBook>) {
        for (book in exchangeOrderBooks) {
            exchangeOrderBookDao.insert(book.toEntity())
        }
    }
}