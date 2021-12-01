package com.kcruz.cryptochallenge.framework.source.local

import com.kcruz.cryptochallenge.data.source.local.IBookLocalSource
import com.kcruz.cryptochallenge.domain.Book
import com.kcruz.cryptochallenge.domain.ExchangeOrderBook
import com.kcruz.cryptochallenge.domain.Order
import com.kcruz.cryptochallenge.framework.database.AppDatabase
import com.kcruz.cryptochallenge.framework.database.entity.ROrder
import com.kcruz.cryptochallenge.framework.source.toEntity
import com.kcruz.cryptochallenge.framework.source.toModel

class BookLocalSource(database: AppDatabase): IBookLocalSource {
    private val exchangeOrderBookDao = database.exchangeOrderBookDao
    private val bookDao = database.bookDao
    private val orderDao = database.orderDao

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

    override fun getBook(bookId: String): Book? {
        return bookDao.getBook(bookId)?.toModel()
    }

    override fun saveBook(book: Book) {
        bookDao.saveBook(book.toEntity())
    }

    override fun saveOrders(_orders: List<Order>, type: String) {
        val orders = mutableListOf<ROrder>()
        for (order in _orders) {
            val orderEntity = order.toEntity()
            orderEntity.type = type
            orders.add(orderEntity)
        }
        orderDao.saveOrders(orders)
    }

    override fun getOrders(book: String, type: String): List<Order> {
        val entityOrders = orderDao.getOrders(book, type)
        val orders = mutableListOf<Order>()
        for (order in entityOrders) {
            orders.add(order.toModel())
        }

        return orders
    }
}