package com.esaudev.wizeline.repository

import com.esaudev.wizeline.data.local.extensions.mapToDomain
import com.esaudev.wizeline.data.local.sources.BitsoLocalDataSource
import com.esaudev.wizeline.data.remote.sources.BitsoRemoteDataSource
import com.esaudev.wizeline.extensions.mapToEntity
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.model.Ticker
import com.esaudev.wizeline.utils.Constants.NETWORK_UNKNOWN_ERROR
import com.esaudev.wizeline.utils.DataState
import com.esaudev.wizeline.utils.InternetConnection.isNetworkAvailable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BitsoRepositoryImpl @Inject constructor(
    private val remoteDataSource: BitsoRemoteDataSource,
    private val localDataSource: BitsoLocalDataSource,
    private val networkDispatcher: CoroutineDispatcher
) : BitsoRepository {

    override suspend fun getAvailableBooks(): DataState<List<AvailableBook>> =
        withContext(networkDispatcher) {
            if (isNetworkAvailable()) {
                when (val networkState = remoteDataSource.getAvailableBooks()) {
                    is DataState.Success -> {
                        localDataSource.deleteAllBooks()
                        localDataSource.insertAllBooks(networkState.data.mapToEntity())
                        val availableBooks = localDataSource.getAllBooks().mapToDomain()
                        DataState.Success(availableBooks)
                    }
                    is DataState.Error -> DataState.Error(networkState.error)
                    else -> DataState.Error(NETWORK_UNKNOWN_ERROR)
                }
            } else {
                val availableBooks = localDataSource.getAllBooks().mapToDomain()
                DataState.Success(availableBooks)
            }
        }

    override suspend fun getTickerFromBook(book: String): DataState<Ticker> =
        withContext(networkDispatcher) {
            if (isNetworkAvailable()){
                when(val networkState = remoteDataSource.getTickerFromBook(book)){
                    is DataState.Success -> {
                        localDataSource.insertBookTicker(networkState.data.mapToEntity())
                        val bookTicker = localDataSource.getTickerByBook(book).mapToDomain()
                        DataState.Success(bookTicker)
                    }
                    is DataState.Error -> DataState.Error(networkState.error)
                    else -> DataState.Error(NETWORK_UNKNOWN_ERROR)
                }
            } else {
                val bookTicker = localDataSource.getTickerByBook(book).mapToDomain()
                DataState.Success(bookTicker)
            }
        }

    override suspend fun getOrderBook(book: String): DataState<OrderBook> =
        withContext(networkDispatcher) {
            if (isNetworkAvailable()){
                when(val networkState = remoteDataSource.getOrderBook(book)){
                    is DataState.Success -> {
                        localDataSource.insertOrderBook(networkState.data.mapToEntity(book))
                        val orderBook = localDataSource.getOrderBookByBook(book).mapToDomain()
                        DataState.Success(orderBook)
                    }
                    is DataState.Error -> DataState.Error(networkState.error)
                    else -> DataState.Error(NETWORK_UNKNOWN_ERROR)
                }
            } else {
                val orderBook = localDataSource.getOrderBookByBook(book).mapToDomain()
                DataState.Success(orderBook)
            }
        }
}