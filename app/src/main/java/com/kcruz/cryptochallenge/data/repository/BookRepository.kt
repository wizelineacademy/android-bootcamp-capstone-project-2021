package com.kcruz.cryptochallenge.data.repository

import com.kcruz.cryptochallenge.commons.Response
import com.kcruz.cryptochallenge.data.source.local.IBookLocalSource
import com.kcruz.cryptochallenge.data.source.remote.IBookRemoteSource
import com.kcruz.cryptochallenge.domain.Orders
import com.kcruz.cryptochallenge.framework.response.AvailableBooksResponse
import com.kcruz.cryptochallenge.framework.response.BookResponse
import com.kcruz.cryptochallenge.framework.response.OrderBookResponse
import com.kcruz.cryptochallenge.usecase.data.IBookRepository
import java.lang.Exception

//TODO: Add documentation

class BookRepository(
    private val remoteSource: IBookRemoteSource,
    private val localSource: IBookLocalSource
) : IBookRepository {

    override suspend fun getAvailableBooks(): Response {
        var response: Response
        try {
            response = remoteSource.getAvailableBooks()

            if (response.body is AvailableBooksResponse && (response.body as AvailableBooksResponse).success) {
                val availableBooks = (response.body as AvailableBooksResponse).payload
                localSource.saveExchangeOrderBooks(availableBooks)
                response.body = availableBooks
            } else {
                val localAvailableBooks = localSource.getExchangeOrderBooks()
                response =
                    Response(if (!localAvailableBooks.isNullOrEmpty()) 200 else 0, localAvailableBooks)
            }
        } catch (e: Exception) {
            val localAvailableBooks = localSource.getExchangeOrderBooks()
            response =
                Response(if (!localAvailableBooks.isNullOrEmpty()) 200 else 0, localAvailableBooks)
        }

        return response
    }

    override suspend fun getBookInfo(book: String): Response {
        var response: Response

        try {
            response = remoteSource.getBookInfo(book)

            if (response.body is BookResponse && (response.body as BookResponse).success) {
                val book = (response.body as BookResponse).payload
                localSource.saveBook(book)
                response.body = book
            } else {
                val localBook = localSource.getBook(book)
                response = Response(if (localBook != null) 200 else 0, localBook)
            }
        } catch (e: Exception) {
            val localBook = localSource.getBook(book)
            response = Response(if (localBook != null) 200 else 0, localBook)
        }

        return response
    }


    override suspend fun getOpenOrders(book: String, aggregate: Boolean?): Response {
        var response: Response
        try {
            response = remoteSource.getOpenOrders(book, aggregate)
            if (response.body is OrderBookResponse && (response.body as OrderBookResponse).success) {
                val orders = (response.body as OrderBookResponse).payload
                localSource.saveOrders(orders.asks, Orders.ASKS)
                localSource.saveOrders(orders.bids, Orders.BIDS)
                response.body = Orders(orders.asks, orders.bids)
            } else {
                val localAsks = localSource.getOrders(book, Orders.ASKS)
                val localBids = localSource.getOrders(book, Orders.BIDS)
                response = Response(if (!localAsks.isNullOrEmpty() || !localBids.isNullOrEmpty()) 200 else 0, Orders(localAsks, localBids))
            }
        } catch (e: Exception) {
            val localAsks = localSource.getOrders(book, Orders.ASKS)
            val localBids = localSource.getOrders(book, Orders.BIDS)
            response = Response(if (!localAsks.isNullOrEmpty() || !localBids.isNullOrEmpty()) 200 else 0, Orders(localAsks, localBids))
        }


        return response
    }


}