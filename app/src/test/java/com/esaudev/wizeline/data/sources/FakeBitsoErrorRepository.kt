package com.esaudev.wizeline.data.sources

import com.esaudev.wizeline.data.remote.sources.BitsoRemoteDataSource
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.model.OrderBook
import com.esaudev.wizeline.model.Ticker
import com.esaudev.wizeline.repository.BitsoRepository
import com.esaudev.wizeline.utils.Constants
import com.esaudev.wizeline.utils.DataState

class FakeBitsoErrorRepository: BitsoRepository {

    override suspend fun getAvailableBooks(): DataState<List<AvailableBook>> {
        return DataState.Error(Constants.NETWORK_UNKNOWN_ERROR)
    }

    override suspend fun getTickerFromBook(book: String): DataState<Ticker> {
        return DataState.Error(Constants.NETWORK_UNKNOWN_ERROR)
    }

    override suspend fun getOrderBook(book: String): DataState<OrderBook> {
        return DataState.Error(Constants.NETWORK_UNKNOWN_ERROR)
    }
}