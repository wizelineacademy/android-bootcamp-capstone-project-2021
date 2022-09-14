package com.esaudev.wizeline.repository

import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.model.Ticker
import com.esaudev.wizeline.utils.DataState

interface BitsoRepository {

    suspend fun getAvailableBooks(): DataState<List<AvailableBook>>

    suspend fun getTickerFromBook(book: String): DataState<Ticker>

    suspend fun getOrderBook(book: String): DataState<OrderBook>
}
