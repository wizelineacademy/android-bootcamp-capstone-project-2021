package com.esaudev.wizeline.repository

import com.esaudev.wizeline.data.remote.sources.BitsoRemoteDataSource
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.model.Ticker
import com.esaudev.wizeline.utils.DataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BitsoRepositoryImpl @Inject constructor(
    private val remoteDataSource: BitsoRemoteDataSource,
    private val networkDispatcher: CoroutineDispatcher
) : BitsoRepository {

    override suspend fun getAvailableBooks(): DataState<List<AvailableBook>> = withContext(networkDispatcher){
        remoteDataSource.getAvailableBooks()
    }

    override suspend fun getTickerFromBook(book: String): DataState<Ticker> = withContext(networkDispatcher) {
        remoteDataSource.getTickerFromBook(book)
    }

    override suspend fun getOrderBook(book: String): DataState<OrderBook> =withContext(networkDispatcher) {
        remoteDataSource.getOrderBook(book)
    }
}