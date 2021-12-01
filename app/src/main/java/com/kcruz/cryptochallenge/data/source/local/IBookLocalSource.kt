package com.kcruz.cryptochallenge.data.source.local

import com.kcruz.cryptochallenge.domain.Book
import com.kcruz.cryptochallenge.domain.ExchangeOrderBook
import com.kcruz.cryptochallenge.domain.Order

interface IBookLocalSource {

    fun getExchangeOrderBooks(): List<ExchangeOrderBook>

    fun saveExchangeOrderBooks(exchangeOrderBooks: List<ExchangeOrderBook>)

    fun getBook(bookId: String): Book?

    fun saveBook(book: Book)

    fun saveOrders(orders: List<Order>, type: String)

    fun getOrders(book: String, type: String): List<Order>
}